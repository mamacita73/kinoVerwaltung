import React, { useState } from "react";
import "../styles/SaalAnlegen.css"; // Import der CSS-Datei

const SaalAnlegen = () => {

    const [anzahlReihen, setAnzahlReihen] = useState(5);
    const kategorieOptionen = ["PARKETT", "LOGE", "LOGE_SERVICE"];

    const generateReihen = (count) => {
        return Array.from({ length: count }, (_, i) => ({
            name: String.fromCharCode(65 + i), // A, B, C, ..., Z
            sitze: 0,
            kategorie: "",
        }));
    };

    const [reihen, setReihen] = useState(generateReihen(anzahlReihen));

    const handleSitzeChange = (index, value) => {
        const updatedReihen = [...reihen];
        updatedReihen[index].sitze = parseInt(value, 10) || 0;
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

    return (
        <div className="saal-container">
            <h2>Saal anlegen</h2>
            <div className="saal-layout">
                <div className="saal-form">
                <div className="reihen-einstellung">
                    <label>Anzahl an Reihen:</label>
                    <input
                        type="number"
                        value={anzahlReihen}
                        onChange={(e) => setAnzahlReihen(Math.min(26, Math.max(1, parseInt(e.target.value, 10) || 1)))}
                        min="1"
                        max="26"
                    />
                    <button className="button" onClick={handleReihenUpdate}>Übernehmen</button>
                </div>

                <div className="reihen-liste">
                    {reihen.map((reihe, index) => (
                        <div key={index} className="reihe">
                            <span>{reihe.name}</span>
                            <input
                                type="number"
                                value={reihe.sitze}
                                onChange={(e) => handleSitzeChange(index, e.target.value)}
                                min="0"
                            />
                            <select
                                value={reihe.kategorie}
                                onChange={(e) => handleKategorieChange(index, e.target.value)}
                            >
                                <option value="">Sitzkategorie</option>
                                {kategorieOptionen.map((kat, i) => (
                                    <option key={i} value={kat}>
                                        {kat}
                                    </option>
                                ))}
                            </select>
                        </div>
                    ))}
                </div>

                <button className="button speichern">Speichern</button>
                </div>
            </div>
        </div>
    );
};

export default SaalAnlegen;
