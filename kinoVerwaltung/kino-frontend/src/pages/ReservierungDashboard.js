import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ReservierungDashboard.css";

const zahlweiseOptions = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];

const ReservierungDashboard = () => {
    const [reservierungen, setReservierungen] = useState([]);
    const [message, setMessage] = useState("");
    const [email, setEmail] = useState(() => localStorage.getItem("loggedInEmail") || "");
    // Mapping: id -> aktuell gewählte Zahlweise
    const [selectedZahlweiseByRes, setSelectedZahlweiseByRes] = useState({});
    const navigate = useNavigate();

    // Reservierungen laden
    const loadReservierungen = async () => {
        if (!email) return;
        try {
            const res = await fetch(`http://localhost:8080/reservierung/byEmail/${email}`);
            const textData = await res.text();
            const data = JSON.parse(textData);
            console.log("Geladene Reservierungen:", data);
            setReservierungen(data);
        } catch (err) {
            console.error("Fehler beim Laden der Reservierungen:", err);
        }
    };

    useEffect(() => {
        loadReservierungen();
    }, [email]);

    // Handler zum Stornieren
    const handleCancel = async (reservierungId) => {
        try {
            // Wir nutzen die numerische ID im Endpoint
            const res = await fetch(`http://localhost:8080/reservierung/${reservierungId}/cancel`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
            });
            if (!res.ok) {
                throw new Error("Fehler beim Senden des Stornierungs-Commands");
            }
            const result = await res.text();
            setMessage("Die Reservierung wurde erfolgreich storniert!");
            loadReservierungen();
        } catch (error) {
            console.error("Stornierungsfehler:", error);
            setMessage("Fehler beim Stornieren: " + error.message);
        }
    };

    // Handler zum Buchen
    const handleBuchen = async (reservierungId) => {
        const zahlweise = selectedZahlweiseByRes[reservierungId] || "EC-KARTE";

        const payload = {
            command: "RESERVIERUNG_BUCHEN",
            payload: {
                // Numerische ID für das Backend
                reservierungId: reservierungId,
                zahlweise: zahlweise,
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
            setMessage(
                "Reservierung erfolgreich zu Buchung umgewandelt! Buchungsnummer: " + result.buchungsnummer
            );

            // State aktualisieren: neu laden + entfernen
            loadReservierungen();
            setReservierungen((prev) => prev.filter((r) => r.id !== reservierungId));
        } catch (error) {
            console.error("Buchungsfehler:", error);
            setMessage("Fehler beim Buchen: " + error.message);
        }
    };

    // Handler zum Aktualisieren der Zahlweise
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
        <div className="container-rd">
            <h2>Meine Reservierungen</h2>
            <label>Kunden E-Mail</label>
            <input type="email" value={email} readOnly/>

            <div className="layout-rd">
                {message && <p className="message">{message}</p>}

                <table className="table">
                    <thead>
                    <tr>
                        <th>Reservierungsinformationen</th>
                        <th>Optionen</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reservierungen.length > 0 ? (
                        reservierungen.map((r) => (
                            <tr key={r.id}>
                                <td>
                                    {/* Hier zeigen wir die (String-) Reservierungsnummer an */}
                                    <p>
                                        <strong>Reservierungsnummer:</strong> {r.reservierungsnummer}
                                    </p>
                                    <p>
                                        <strong>Film:</strong> {r.filmTitel || "-"}
                                    </p>
                                    <p>
                                        <strong>Kategorie:</strong> {r.kategorie || "-"}
                                    </p>
                                    <p>
                                        <strong>Anzahl Plätze:</strong> {r.anzahl || "-"}
                                    </p>
                                    <p>
                                        <strong>Summe (€):</strong> {r.summe || "-"}
                                    </p>
                                </td>
                                <td>
                                    <div>
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
                                    </div>
                                    <div>
                                        <div className="button-container-rd">
                                            <button className="button-rd" onClick={() => handleCancel(r.id)}>
                                                Stornieren
                                            </button>
                                            <button className="button-rd" onClick={() => handleBuchen(r.id)}>
                                                Buchen
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="2">Keine Reservierungen gefunden.</td>
                        </tr>
                    )}
                    </tbody>
                </table>

            </div>
            <button className="button-rd" onClick={handleBack}>
                Zurück
            </button>
        </div>
    );
};

export default ReservierungDashboard;
