
import { useNavigate } from "react-router-dom";
import "../styles/Filmplannung.css"; // Import der CSS-Datei
import React, { useState, useEffect } from "react";


const Filmplanung = () => {
    const [saele, setSaele] = useState([]);  // Hier speichern wir Säle + Vorstellungen
    const navigate = useNavigate();

    // Beim Laden der Komponente Säle + Vorstellungen laden
    useEffect(() => {
        fetch("http://localhost:8080/saal/mitVorstellungen")
            .then((res) => res.json())
            .then((data) => {
                console.log("Geladene Säle:", data);
                setSaele(data);
            })
            .catch((err) => console.error("Fehler beim Laden der Säle:", err));
    }, []);

    // Hilfsfunktion, um Endzeit zu berechnen
    const calculateEndTime = (startStr, dauerMinuten) => {
        // Startzeit z. B. "16:00" -> [16, 00]
        const [hh, mm] = startStr.split(":");
        let startTime = new Date();
        startTime.setHours(hh);
        startTime.setMinutes(mm);

        // Dauer hinzufügen
        startTime.setMinutes(startTime.getMinutes() + dauerMinuten);

        // Ergebnis als HH:mm zurück
        const endH = String(startTime.getHours()).padStart(2, "0");
        const endM = String(startTime.getMinutes()).padStart(2, "0");
        return `${endH}:${endM}`;
    };

    // Navigation zu SaalAnlegen
    const handleGoToSaalAnlegen = () => {
        navigate("/SaalAnlegen");
    };

    // Navigation zu VorstellungAnlegen
    const handleGoToVorstellungAnlegen = () => {
        navigate("/VorstellungAnlegen");
    };

    return (
        <div className="container-p">
            <h2>Filmplanung</h2>

            <div className="layout-p">


                <div className="saal-container-p">
                    <h3>Vorhandene Säle:</h3>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Saal</th>
                            <th>Filme</th>
                            <th>Start</th>
                            <th>Ende</th>
                        </tr>
                        </thead>
                        <tbody>
                        {saele.map((s) =>
                            // Wenn der Saal Vorstellungen hat, jede Vorstellung in eigener Zeile
                            s.vorstellungen && s.vorstellungen.length > 0 ? (
                                s.vorstellungen.map((v) => (
                                    <tr key={v.id}>
                                        <td>{s.name}</td>
                                        <td>{v.filmTitel}</td>
                                        <td>{v.startzeit}</td>
                                        <td>{calculateEndTime(v.startzeit, v.dauerMinuten)}</td>
                                    </tr>
                                ))
                            ) : (
                                // Falls keine Vorstellung vorhanden
                                <tr key={s.id}>
                                    <td>{s.name}</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>-</td>
                                </tr>
                            )
                        )}
                        </tbody>
                    </table>


                    <button className="button-p" onClick={handleGoToSaalAnlegen}>
                        Saal anlegen
                    </button>
                    <button className="button-p" onClick={handleGoToVorstellungAnlegen}>
                        Vorstellung anlegen
                    </button>

                </div>
            </div>
        </div>
    );
};

export default Filmplanung;
