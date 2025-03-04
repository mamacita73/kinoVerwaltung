import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ReservierungDashboard.css";

const zahlweiseOptions = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];

const ReservierungDashboard = () => {
    const [reservierungen, setReservierungen] = useState([]);
    const [message, setMessage] = useState("");
    const [email, setEmail] = useState(() => localStorage.getItem("loggedInEmail") || "");
    // Für jede Reservierung speichern wir die aktuell gewählte Zahlweise (Mapping: reservierungId -> zahlweise)
    const [selectedZahlweiseByRes, setSelectedZahlweiseByRes] = useState({});
    const navigate = useNavigate();

    // Laden der Reservierungen für die aktuell eingeloggte Email
    const loadReservierungen = async () => {
        if (!email) return;
        try {
            const res = await fetch(`http://localhost:8080/reservierung/byEmail/${email}`);

            const textData = await res.text();
            const data = JSON.parse(textData);
            console.log("textData", textData);
            console.log("Geladene Reservierungen:", data);
            setReservierungen(data);
        } catch (err) {
            console.error("Fehler beim Laden der Reservierungen:", err);
        }
    };

    useEffect(() => {
        loadReservierungen();
    }, [email]);

    // Handler zum Stornieren einer Reservierung
    const handleCancel = async (reservierungId) => {
        try {
            const res = await fetch(`http://localhost:8080/reservierung/${reservierungId}/cancel`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
            });
            if (!res.ok) {
                throw new Error("Fehler beim Senden des Stornierungs-Commands");
            }
            const result = await res.text();
            setMessage(result);
            // Reservierungen neu laden
            loadReservierungen();
        } catch (error) {
            console.error("Stornierungsfehler:", error);
            setMessage("Fehler beim Stornieren: " + error.message);
        }
    };

    // Handler zum Buchen einer Reservierung (Umwandlung in Buchung)
    const handleBuchen = async (reservierungId) => {
        // Hole die ausgewählte Zahlweise für diese Reservierung; falls nicht gewählt, setze einen Standardwert
        const zahlweise = selectedZahlweiseByRes[reservierungId] || "EC-KARTE";

        // Baue die Payload für den Command "RESERVIERUNG_BUCHEN"
        const payload = {
            command: "RESERVIERUNG_BUCHEN",
            payload: {
                reservierungId: reservierungId,
                zahlweise: zahlweise, // Falls dein Backend das unterstützt
            },
        };
        try {
            const res = await fetch("http://localhost:8080/buchung/reservierung", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });
            const result = await res.json();
            console.log("Buchungsergebnis:", result);
            if (!res.ok) {
                throw new Error(result.error || "Fehler beim Senden des Buchungs-Commands");
            }
            setMessage("Reservierung erfolgreich zu Buchung umgewandelt! Buchungsnummer: " + result.buchungsnummer);
            // Reservierungen neu laden
            loadReservierungen();
        } catch (error) {
            console.error("Buchungsfehler:", error);
            setMessage("Fehler beim Buchen: " + error.message);
        }
    };

    // Handler, um die ausgewählte Zahlweise für eine Reservierung zu aktualisieren
    const handleZahlweiseChange = (reservierungId, newZahlweise) => {
        setSelectedZahlweiseByRes((prev) => ({
            ...prev,
            [reservierungId]: newZahlweise,
        }));
    };

    // Navigation
    const handleBack = () => {
        navigate("/KundenDashboard");
    };

    return (
        <div className="reservierung-container-rd">
            <h2>Meine Reservierungen</h2>
            {message && <p className="message">{message}</p>}
            <table className="table">
                <thead>
                <tr>
                    <th>Reservierungs-ID</th>
                    <th>Film</th>
                    <th>Kategorie</th>
                    <th>Anzahl Plätze</th>
                    <th>Summe (€)</th>
                    <th>Zahlweise</th>
                    <th>Aktionen</th>
                </tr>
                </thead>
                <tbody>
                {reservierungen.length > 0 ? (
                    reservierungen.map((r) => (
                        <tr key={r.id}>
                            <td>{r.id}</td>
                            <td>{r.filmTitel || "-"}</td>
                            <td>{r.kategorie || "-"}</td>
                            <td>{r.anzahl || "-"}</td>
                            <td>{r.summe || "-"}</td>
                            <td>
                                <select
                                    value={selectedZahlweiseByRes[r.id] || ""}
                                    onChange={(e) => handleZahlweiseChange(r.id, e.target.value)}
                                >
                                    <option value="">Zahlweise wählen</option>
                                    {zahlweiseOptions.map((z, idx) => (
                                        <option key={idx} value={z}>
                                            {z}
                                        </option>
                                    ))}
                                </select>
                            </td>
                            <td>
                                <button onClick={() => handleCancel(r.id)}>Stornieren</button>
                                <button onClick={() => handleBuchen(r.id)}>Buchen</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="7">Keine Reservierungen gefunden.</td>
                    </tr>
                )}
                </tbody>
            </table>
            <button className="back-button" onClick={handleBack}>
                Zurück
            </button>
        </div>
    );
};

export default ReservierungDashboard;
