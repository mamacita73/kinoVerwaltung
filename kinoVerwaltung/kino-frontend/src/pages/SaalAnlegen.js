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
        <div className="saal-container-s">
            <h2>Saal anlegen</h2>
            <div className="saal-layout-s">
                <div className="saal-form-s">
                <div className="reihen-einstellung-s">
                    <label>Anzahl an Reihen:</label>
                    <input
                        className="input-s"
                        type="number"
                        value={anzahlReihen}
                        onChange={(e) => setAnzahlReihen(Math.min(26, Math.max(1, parseInt(e.target.value, 10) || 1)))}
                        min="1"
                        max="26"
                    />
                    <button className="button-s" onClick={handleReihenUpdate}>Übernehmen</button>
                </div>

                <div className="reihen-liste-s">
                    {reihen.map((reihe, index) => (
                        <div key={index} className="reihe-s">
                            <span>{reihe.name}</span>
                            <input
                                className="input-s"
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

                <button className="button-s">Speichern</button>
                </div>
            </div>
        </div>
    );
};

export default SaalAnlegen;
