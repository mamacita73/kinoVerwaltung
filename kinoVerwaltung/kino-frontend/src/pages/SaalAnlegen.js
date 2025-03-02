import React, { useState } from "react";
import "../styles/SaalAnlegen.css"; // Import der CSS-Datei

import { useNavigate } from "react-router-dom";

const SaalAnlegen = () => {

    const [saalName, setSaalName] = useState("");
    const [anzahlReihen, setAnzahlReihen] = useState(5);
    const kategorieOptionen = ["PARKETT", "LOGE", "LOGE_SERVICE"];
    const navigate = useNavigate();

    const generateReihen = (count) => {
        return Array.from({ length: count }, (_, i) => ({
            reihenBezeichnung: String.fromCharCode(65 + i), // "A", "B", "C", ...
            sitzeAnzahl: 0,  // Anzahl der Sitze in dieser Reihe
            kategorie: ""   //  "PARKETT", "LOGE", "LOGE_SERVICE"
        }));
    };

    const [reihen, setReihen] = useState(generateReihen(anzahlReihen));

    const handleSitzeChange = (index, value) => {
        const updatedReihen = [...reihen];
        updatedReihen[index].sitzeAnzahl = parseInt(value, 10) || 0;
        setReihen(updatedReihen);
    };

    const handleKategorieChange = (index, value) => {
        const updatedReihen = [...reihen];
        updatedReihen[index].kategorie = value;
        setReihen(updatedReihen);
    };

    const handleReihenUpdate = () => {
        setReihen(generateReihen(anzahlReihen));
    };
    const handleGoToFilmplanung = () => {
        navigate("/FilmPlanung");
    };

    const handleSave = async () => {
        // Für jede Reihe: Objekt mit Bezeichnung, Sitzanzahl und einem Array an Sitzen
        //  Sitze basierend auf der eingegebenen "sitzeAnzahl" und Kategorie erstellt
        const sitzreihen = reihen.map((row) => {
            const sitze = Array.from({ length: row.sitzeAnzahl }, (_, i) => ({
                nummer: i + 1,
                kategorie: row.kategorie,
                status: "FREI" // Standardstatus
            }));
            return {
                reihenBezeichnung: row.reihenBezeichnung,
                anzahlSitze: row.sitzeAnzahl,
                sitze: sitze
            };
        });

        // Erstelle den vollständigen Payload, der an das Backend gesendet wird.
        const payload = {
            command: "SAAL_WRITE",
            payload: {
                name: saalName,
                anzahlReihen: anzahlReihen,
                istFreigegeben: true,
                sitzreihen: sitzreihen //  Array mit den Sitzreihen (inkl. Sitzen) übergeben
            }
        };

        console.log("Sende Payload:", JSON.stringify(payload, null, 2));

        try {
            const response = await fetch("http://localhost:8080/saal/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });
            console.log("Response Status:", response.status);
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen des Saals");
            }
            const result = await response.json();
            console.log("Response Data:", result);
        } catch (error) {
            console.error("Fehler:", error);
        }
    };


    return (
        <div className="saal-container-s">
            <h2>Saal anlegen</h2>
            <div className="saal-layout-s">
                <div className="saal-form-s ">
                    <div className="reihen-einstellung-s">
                        <label>Saal Name:</label>
                        <input
                            className="input-s"
                            value={saalName}
                            onChange={(e) => setSaalName(e.target.value)}
                            placeholder="Name des Saals"
                        />
                    </div>
                    <label>Anzahl der Reihen:</label>
                    <input
                        type="number"
                        value={anzahlReihen}
                        onChange={(e) => setAnzahlReihen(parseInt(e.target.value, 10) || 1)}
                        min="1"
                        max="26"
                    />
                    <button className="button-s" onClick={handleReihenUpdate}>Reihen aktualisieren</button>


                    <div className="reihen-liste-s">
                        {reihen.map((row, index) => (
                            <div key={index} className="reihe-s">
                                <span>{row.reihenBezeichnung}</span>
                                <input
                                    className="input-s"
                                    type="number"
                                    placeholder="Anzahl Sitze"
                                    value={row.sitzeAnzahl}
                                    onChange={(e) => handleSitzeChange(index, e.target.value)}
                                    min="0"
                                />
                                <select
                                    value={row.kategorie}
                                    onChange={(e) => handleKategorieChange(index, e.target.value)}
                                >
                                    <option value="">Kategorie wählen</option>
                                    {kategorieOptionen.map((kat, i) => (
                                        <option key={i} value={kat}>
                                            {kat}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        ))}
                    </div>
                    <button className="button-s" onClick={handleGoToFilmplanung}>Zurück</button>
                    <button className="button-s" onClick={handleSave}>Speichern</button>
                </div>
            </div>
        </div>
    );
};

export default SaalAnlegen;
