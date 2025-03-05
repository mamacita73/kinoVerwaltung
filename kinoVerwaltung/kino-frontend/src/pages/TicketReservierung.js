import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/TicketReservierung.css";

const TicketReservierung = () => {
    const [vorstellungen, setVorstellungen] = useState([]);
    const [vorstellungId, setVorstellungId] = useState("");
    const [kategorie, setKategorie] = useState("PARKETT");
    const [anzahl, setAnzahl] = useState(1);
    const [kundenEmail, setKundenEmail] = useState(() => localStorage.getItem("loggedInEmail") || "");
    const [freiePlaetze, setFreiePlaetze] = useState(0);
    const [message, setMessage] = useState("");
    const [reservierungsnummer, setReservierungsnummer] = useState("");
    const kategorien = ["PARKETT", "LOGE", "LOGE_SERVICE"];
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/vorstellung")
            .then(res => res.json())
            .then(data => setVorstellungen(data))
            .catch(err => console.error("Fehler beim Laden der Vorstellungen:", err));
    }, []);

    useEffect(() => {
        if (!vorstellungId || !kategorie) {
            setFreiePlaetze(0);
            return;
        }
        const url = `http://localhost:8080/vorstellung/${vorstellungId}/verfuegbar?kategorie=${kategorie}`;
        console.log("Verfügbarkeitsabfrage:", url);
        fetch(url)
            .then(res => res.json())
            .then(data => {
                if (data && typeof data.freiePlaetze === "number") {
                    setFreiePlaetze(data.freiePlaetze);
                } else {
                    setFreiePlaetze(0);
                }
            })
            .catch(err => {
                console.error("Fehler bei der Verfügbarkeitsabfrage:", err);
                setFreiePlaetze(0);
            });
    }, [vorstellungId, kategorie]);

    const handleReservierung = async () => {
        if (!vorstellungId || !kategorie || !anzahl) {
            alert("Bitte Vorstellung, Kategorie und Anzahl wählen!");
            return;
        }
        if (anzahl > freiePlaetze) {
            alert("Nicht genügend freie Plätze verfügbar!");
            return;
        }
        const payload = {
            command: "RESERVIERUNG_WRITE",
            payload: {
                vorstellungId: parseInt(vorstellungId, 10),
                kategorie,
                anzahl: parseInt(anzahl, 10),
                kundenEmail,
                datum: "2025-03-02",
                status: "RESERVIERT"
            }
        };
        try {
            const response = await fetch("http://localhost:8080/reservierung/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json",
                           "X-User-Email": kundenEmail},
                body: JSON.stringify(payload)
            });
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Reservierung");
            }
            const result = await response.json();
            console.log("Reservierungsergebnis (JSON-Objekt):", result);

            if (result.reservierungsnummer) {
                setReservierungsnummer(result.reservierungsnummer);
                setMessage("Reservierung erfolgreich! Nr.: " + result.reservierungsnummer);
            } else {
                setMessage("Reservierung erfolgreich, aber keine Nummer gefunden.");
            }
        } catch (error) {
            console.error("Fehler:", error);
            setMessage("Fehler beim Reservieren: " + error.message);
        }
    };

    const handleZuReservierungen = () => {
        navigate("/ReservierungDashboard");
    };
    const handleBack = () => {
        navigate("/KundenDashboard");
    };

    return (
        <div className="reservierung-container-tr">
            <h2>Tickets reservieren</h2>
            <div className="form-grid-tr">
                <label>Kunden-E-Mail:</label>
                <input type="email" value={kundenEmail} readOnly />
                <label>Vorstellung wählen:</label>
                <select value={vorstellungId} onChange={(e) => setVorstellungId(e.target.value)}>
                    <option value="">-- Bitte wählen --</option>
                    {vorstellungen.map((v) => (
                        <option key={v.id} value={v.id}>
                            {v.filmTitel} (SaalID={v.saalId != null ? v.saalId : "?"}), Start: {v.startzeit}
                        </option>
                    ))}
                </select>
                <label>Kategorie:</label>
                <select value={kategorie} onChange={(e) => setKategorie(e.target.value)}>
                    {kategorien.map((kat) => (
                        <option key={kat} value={kat}>
                            {kat}
                        </option>
                    ))}
                </select>
                <label>Anzahl freie Plätze:</label>
                <input type="number" value={freiePlaetze} readOnly />
                <label>Anzahl Plätze:</label>
                <input type="number" min={1} value={anzahl} onChange={(e) => setAnzahl(e.target.value)} />
            </div>
            {message && <p>{message}</p>}
            <div className="reservierung-footer-tr">
                <label>Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly/>


                <div className="button-container-tr">
                    <button className="button-tr" onClick={handleReservierung}>Reservieren</button>
                    <button className="button-tr" onClick={handleZuReservierungen}>Zu den Reservierungen</button>
                    <button className="button-tr" onClick={handleBack}>Zurück</button>
                </div>
            </div>
        </div>
    );
};

export default TicketReservierung;
