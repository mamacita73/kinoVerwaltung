
import "../styles/TicketReservierung.css"; // Import der CSS-Datei
import React, { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";

const TicketReservierung = () => {
    const [vorstellungen, setVorstellungen] = useState([]);
    const [vorstellungId, setVorstellungId] = useState("");
    const [kategorie, setKategorie] = useState("PARKETT");
    const [anzahl, setAnzahl] = useState(1);
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const kategorien = ["PARKETT", "LOGE", "LOGE_SERVICE"];
    // Kunden-E-Mail aus dem localStorage laden
    const [kundenEmail, setKundenEmail] = useState(() => {
        return localStorage.getItem("loggedInEmail") || "";
    });
    const [reservierungsnummer, setReservierungsnummer] = useState("");
    const [verfügbar, setVerfügbar] = useState({
        PARKETT: 0,
        LOGE: 0,
        LOGE_SERVICE: 0
    });

    const handleZuReservierungen = () => {
        navigate("/ReservierungDashboard");
    };

    useEffect(() => {
        const email = localStorage.getItem("loggedInEmail") || "";
        setKundenEmail(email);
    }, []);

    // Alle Vorstellungen
    useEffect(() => {
        fetch("http://localhost:8080/vorstellung")
            .then((res) => res.json())
            .then((data) => setVorstellungen(data))
            .catch((err) =>
                console.error("Fehler beim Laden der Vorstellungen:", err)
            );
    }, []);

    // pürfen ob verfübgar
    useEffect(() => {
        if (!vorstellungId) {
            setVerfügbar({ PARKETT: 0, LOGE: 0, LOGE_SERVICE: 0 });
            return;
        }

        fetch(`http://localhost:8080/vorstellung/${vorstellungId}/verfügbar`)
            .then((res) => res.json())
            .then((data) => setVerfügbar(data))
            .catch((err) =>
                console.error("Fehler beim Laden der Sitzverfügbarkeit:", err)
            );
    }, [vorstellungId]);

    const handleReservierung = async () => {
        const payload = {
            command: "RESERVIERUNG_WRITE",
            payload: {
                vorstellungId: parseInt(vorstellungId, 10),
                kategorie,
                anzahl: parseInt(anzahl, 10),
                kundenEmail, // aus dem localstorage
                datum: "2025-03-02",
                status: "RESERVIERT"
            }
        };

        try {
            const response = await fetch("http://localhost:8080/reservierung/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json",
                    "X-User-Email": kundenEmail
                },
                body: JSON.stringify(payload),
            });
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Reservierung");
            }
            const result = await response.json();
            setMessage("Reservierung erfolgreich angelegt: " + JSON.stringify(result));

            if (result && result.reservierungsnummer) {
                setReservierungsnummer(result.reservierungsnummer);
            }

            // **Neue Verfügbarkeit laden**, damit die Anzeige aktualisiert wird
            // (nur falls eine Vorstellung gewählt ist)
            if (vorstellungId) {
                const verfuegbarRes = await fetch(
                    `http://localhost:8080/vorstellung/${vorstellungId}/verfügbar`
                );
                const verfuegbarData = await verfuegbarRes.json();
                setVerfügbar(verfuegbarData);
            }
        } catch (error) {
            console.error("Fehler:", error);
            setMessage("Fehler beim Reservieren: " + error.message);
        }
    };

    return (
        <div className="reservierung-container-tr">
            <h2>Tickets reservieren</h2>

            <div className="form-grid-tr">
                <label>Kunden-E-Mail:</label>
                <input
                    type="email"
                    value={kundenEmail}
                    onChange={(e) => setKundenEmail(e.target.value)}
                    readOnly
                />

                <label>Vorstellung wählen:</label>
                <select
                    value={vorstellungId}
                    onChange={(e) => setVorstellungId(e.target.value)}
                >
                    <option value="">-- Bitte wählen --</option>
                    {vorstellungen.map((v) => (
                        <option key={v.id} value={v.id}>
                            {v.filmTitel} (SaalID={v.saalId}), Start: {v.startzeit}
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
                <div style={{gridColumn: "1 / span 2", margin: "0.5rem 0"}}>
                    <strong>Freie Plätze ({kategorie}): </strong>
                    {verfügbar[kategorie]}
                </div>

                <label>Anzahl Plätze:</label>
                <input
                    type="number"
                    value={anzahl}
                    onChange={(e) => setAnzahl(e.target.value)}
                    min={1}
                />


                {message && <p>{message}</p>}
            </div>


            <div className="reservierung-footer-tr">
                <label>Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly/>

                <button className="button-tr" onClick={handleReservierung}>Reservieren</button>
                <button className="button-tr" onClick={handleZuReservierungen}>zu den Reservierungen</button>
            </div>
        </div>
    );
};

export default TicketReservierung;

