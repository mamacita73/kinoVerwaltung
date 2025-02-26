import React, { useState } from "react";
import "../styles/FilmDashboard.css"; // Import der CSS-Datei

const FilmDashboard = () => {
    const [selectedFilm, setSelectedFilm] = useState("");
    const [einnahmen, setEinnahmen] = useState("");

    const filme = ["Film A", "Film B", "Film C"];

    const handleFilmChange = (event) => {
        setSelectedFilm(event.target.value);
        // Hier könnte man eine API-Abfrage für Einnahmen hinzufügen
        setEinnahmen(Math.floor(Math.random() * 10000) + " €"); // Platzhalter-Wert
    };

    return (
        <div className="dashboard-container">
            <h2>Film Dashboard</h2>

            <div className="dashboard-content">
                <label className="label">Film:</label>
                <select className="select" value={selectedFilm} onChange={handleFilmChange}>
                    <option value="">Filme</option>
                    {filme.map((film, index) => (
                        <option key={index} value={film}>
                            {film}
                        </option>
                    ))}
                </select>

                <label className="label">Gesamteinnahmen:</label>
                <input className="input" type="text" value={einnahmen} readOnly />
            </div>
        </div>
    );
};

export default FilmDashboard;
