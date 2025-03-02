import React, { useState, useEffect } from "react";
import "../styles/VorstellungAnlegen.css";
import { useNavigate } from "react-router-dom";

const MultiVorstellungAnlegen = () => {
    const [saele, setSaele] = useState([]);
    const [filmTitel, setFilmTitel] = useState("");
    const [dauerMinuten, setDauerMinuten] = useState(90);
    // Wir speichern hier eine Liste von Paaren: { saalId, startzeit }
    const [auffuehrungen, setAuffuehrungen] = useState([{ saalId: "", startzeit: "" }]);
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    // Säle laden
    useEffect(() => {
        fetch("http://localhost:8080/saal")
            .then((res) => res.json())
            .then((data) => {
                console.log("Geladene Säle:", data);
                setSaele(data);
            })
            .catch((err) => console.error("Fehler beim Laden der Säle:", err));
    }, []);

    const handleGoToFilmplanung = () => {
        navigate("/FilmPlanung");
    };

    // Hinzufügen eines neuen Aufführungsfelds
    const addAuffuehrung = () => {
        setAuffuehrungen([...auffuehrungen, { saalId: "", startzeit: "" }]);
    };

    // Handler für Änderung in den Aufführungsfeldern
    const handleAuffuehrungChange = (index, field, value) => {
        const newAuffuehrungen = [...auffuehrungen];
        newAuffuehrungen[index][field] = value;
        setAuffuehrungen(newAuffuehrungen);
    };

    // Speichern-Handler
    const handleSave = async () => {
        // Vorprüfungen: Filmtitel darf nicht leer sein und alle Aufführungen müssen ausgefüllt sein.
        if (!filmTitel) {
            setMessage("Bitte geben Sie einen Filmtitel ein.");
            return;
        }
        for (let i = 0; i < auffuehrungen.length; i++) {
            if (!auffuehrungen[i].saalId || !auffuehrungen[i].startzeit) {
                setMessage("Bitte füllen Sie alle Aufführungsfelder aus.");
                return;
            }
        }
        // Erstelle Arrays für saalIds und startzeiten
        const saalIds = auffuehrungen.map(a => parseInt(a.saalId, 10));
        const startzeiten = auffuehrungen.map(a => a.startzeit);

        const payload = {
            filmTitel,
            dauerMinuten,
            saalIds,
            startzeiten
        };

        console.log("Sende MultiVorstellung Payload:", payload);

        try {
            const response = await fetch("http://localhost:8080/vorstellung/anlegenMulti", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Vorstellungen");
            }
            const result = await response.json();
            console.log("Vorstellungen erfolgreich angelegt:", result);
            setMessage("Vorstellungen wurden erfolgreich angelegt!");
            // Formular zurücksetzen
            setFilmTitel("");
            setDauerMinuten(90);
            setAuffuehrungen([{ saalId: "", startzeit: "" }]);
        } catch (error) {
            console.error("Fehler:", error);
            setMessage("Fehler beim Anlegen der Vorstellungen: " + error.message);
        }
    };

    return (
        <div className="vorstellung-container-v">
            <h2>Mehrfache Vorstellungen anlegen</h2>
            <div className="vorstellung-form-v">
                <div className="form-row">
                    <label>Filmtitel:</label>
                    <input
                        value={filmTitel}
                        onChange={(e) => setFilmTitel(e.target.value)}
                        placeholder="Name des Films"
                    />
                </div>
                <div className="form-row">
                    <label>Filmdauer (Min.):</label>
                    <input
                        type="number"
                        value={dauerMinuten}
                        onChange={(e) => setDauerMinuten(parseInt(e.target.value, 10) || 0)}
                    />
                </div>
                <h3>Aufführungen:</h3>
                {auffuehrungen.map((a, index) => (
                    <div key={index} className="auffuehrung-row">
                        <label>Saal:</label>
                        <select
                            value={a.saalId}
                            onChange={(e) => handleAuffuehrungChange(index, "saalId", e.target.value)}
                        >
                            <option value="">-- Bitte wählen --</option>
                            {saele.map((s) => (
                                <option key={s.id} value={s.id}>
                                    {s.name} (ID={s.id})
                                </option>
                            ))}
                        </select>
                        <label>Startzeit:</label>
                        <input
                            value={a.startzeit}
                            onChange={(e) => handleAuffuehrungChange(index, "startzeit", e.target.value)}
                            placeholder="z.B. 16:00"
                        />
                    </div>
                ))}
                <button className="button-v" onClick={addAuffuehrung}>
                    Weitere Aufführung hinzufügen
                </button>
                <div className="button-row">
                    <button className="button-v" onClick={handleGoToFilmplanung}>
                        Zurück
                    </button>
                    <button className="button-v" onClick={handleSave}>
                        Speichern
                    </button>
                </div>
                {message && <p>{message}</p>}
            </div>
        </div>
    );
};

export default MultiVorstellungAnlegen;
