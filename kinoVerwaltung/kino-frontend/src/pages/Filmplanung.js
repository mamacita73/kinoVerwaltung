import React, { useState } from "react";
import "../styles/Filmplannung.css"; // Import der CSS-Datei

const Filmplanung = () => {
    const [saele, setSaele] = useState([
        { id: 1, name: "Saal 1", film: "", start: "16:00", end: "17:30" },
        { id: 2, name: "Saal 2", film: "", start: "", end: "" },
    ]);

    const filme = ["Film A", "Film B", "Film C"];

    const handleFilmChange = (index, value) => {
        const updatedSaele = [...saele];
        updatedSaele[index].film = value;
        setSaele(updatedSaele);
    };

    return (
        <div className="container">
            <h2>Filmplanung</h2>

            {/* Wrapper für Buttons + Tabelle */}
            <div className="layout">
                {/* Buttons links */}
                <div className="button-container">
                    <button className="button">Saal anlegen</button>
                    <button className="button">Film Dashboard</button>
                </div>

                {/* Tabelle rechts */}
                <div className="saal-container">
                    <h3>Vorhandene Säle: <span className="bearbeiten">Bearbeiten</span></h3>
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
                        {saele.map((saal, index) => (
                            <tr key={saal.id}>
                                <td>{saal.name}</td>
                                <td>
                                    <select
                                        value={saal.film}
                                        onChange={(e) => handleFilmChange(index, e.target.value)}
                                        className="select"
                                    >
                                        <option value="">Filme</option>
                                        {filme.map((film, i) => (
                                            <option key={i} value={film}>
                                                {film}
                                            </option>
                                        ))}
                                    </select>
                                </td>
                                <td>{saal.start}</td>
                                <td>{saal.end}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Filmplanung;
