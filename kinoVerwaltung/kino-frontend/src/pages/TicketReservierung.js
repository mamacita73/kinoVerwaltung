import "../styles/TicketReservierung.css";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

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
    const handleBack = () => {
        navigate("/KundenDashboard");
    };

    useEffect(() => {
        const email = localStorage.getItem("loggedInEmail") || "";
        setKundenEmail(email);
    }, []);

    useEffect(() => {
        fetch("http://localhost:8080/vorstellung")
            .then((res) => res.json())
            .then((data) => setVorstellungen(data))
            .catch((err) => console.error("Fehler beim Laden der Vorstellungen:", err));
    }, []);

    useEffect(() => {
        const fetchVerfügbarkeit = async () => {
            if (!vorstellungId) {
                setVerfügbar({ PARKETT: 0, LOGE: 0, LOGE_SERVICE: 0 });
                return;
            }

            try {
                const res = await fetch(`http://localhost:8080/vorstellung/${vorstellungId}/verfuegbar`);

                if (!res.ok) {
                    throw new Error(`Server-Fehler: ${res.status}`);
                }

                const text = await res.text();
                if (!text) {
                    console.warn("Leere Antwort vom Server für Verfügbarkeit!");
                    return;
                }

                const data = JSON.parse(text);
                setVerfügbar(data);
            } catch (err) {
                console.error("Fehler beim Abrufen der Verfügbarkeit:", err);
            }
        };

        fetchVerfügbarkeit();
    }, [vorstellungId]);

    const handleReservierung = async () => {
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
                headers: {
                    "Content-Type": "application/json",
                    "X-User-Email": kundenEmail
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Reservierung");
            }

            const text = await response.text();
            if (!text) {
                throw new Error("Leere Antwort vom Server");
            }

            const result = JSON.parse(text);
            setMessage("Reservierung erfolgreich angelegt: " + JSON.stringify(result));

            if (result && result.reservierungsnummer) {
                setReservierungsnummer(result.reservierungsnummer);
            }

            if (vorstellungId) {
                const verfuegbarRes = await fetch(`http://localhost:8080/vorstellung/${vorstellungId}/verfuegbar`);
                const verfuegbarText = await verfuegbarRes.text();
                if (verfuegbarText) {
                    setVerfügbar(JSON.parse(verfuegbarText));
                }
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
                <input type="email" value={kundenEmail} readOnly />

                <label>Vorstellung wählen:</label>
                <select value={vorstellungId} onChange={(e) => setVorstellungId(e.target.value)}>
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
                        <option key={kat} value={kat}>{kat}</option>
                    ))}
                </select>

                <label>Anzahl Plätze:</label>
                <input type="number" value={anzahl} onChange={(e) => setAnzahl(e.target.value)} min={1} />

                {message && <p>{message}</p>}
            </div>

            <div className="reservierung-footer-tr">
                <label>Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly/>

                <button className="button-tr" onClick={handleReservierung}>
                    Reservieren
                </button>
                <button className="button-tr" onClick={handleZuReservierungen}>
                    Reservierungen verwalten
                </button>
                <button className="button-tr" onClick={handleBack}>
                    Zurück
                </button>
            </div>
        </div>
    );
};

export default TicketReservierung;
