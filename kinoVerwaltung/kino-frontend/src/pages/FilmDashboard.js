import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/FilmDashboard.css"; // Import der CSS-Datei

const FilmDashboard = () => {
    const navigate = useNavigate();

    // Aus dem Endpoint /saal/mitVorstellungen laden wir alle Säle inkl. Vorstellungen
    const [saele, setSaele] = useState([]);

    // Liste aller Filmtitel (distinct)
    const [filme, setFilme] = useState([]);

    // Ausgewählter Film im Dropdown
    const [selectedFilm, setSelectedFilm] = useState("");

    // Summierte Einnahmen für den ausgewählten Film
    const [einnahmen, setEinnahmen] = useState("");

    const handleGoToFilmPlanung = () => {
        navigate("/FilmPlanung");
    };


    // Säle + Vorstellungen laden
    useEffect(() => {
        fetch("http://localhost:8080/saal/mitVorstellungen")
            .then((res) => res.json())
            .then((data) => {
                console.log("Geladene Säle:", data);
                setSaele(data);
            })
            .catch((err) => console.error("Fehler beim Laden der Säle:", err));
    }, []);

    // Wenn Säle geladen sind -> distinct Filmtitel extrahieren
    useEffect(() => {
        // Erstelle eine Set-Struktur, um Duplikate zu vermeiden
        const filmSet = new Set();

        saele.forEach((s) => {
            if (s.vorstellungen && s.vorstellungen.length > 0) {
                s.vorstellungen.forEach((v) => {
                    filmSet.add(v.filmTitel);
                });
            }
        });

        // Aus Set ein Array machen
        const filmArray = Array.from(filmSet);
        console.log("Distinct Filme:", filmArray);
        setFilme(filmArray);
    }, [saele]);

    // Handler bei Film-Auswahl -> Summierte Einnahmen aus Mongo laden
    const handleFilmChange = async (event) => {
        const filmTitel = event.target.value;
        setSelectedFilm(filmTitel);

        if (!filmTitel) {
            // Wenn der Nutzer den Dropdown auf "" setzt
            setEinnahmen("");
            return;
        }

        // Hol dir alle Stats für diesen Film
        try {
            const url = `http://localhost:8080/stats/film/${filmTitel}`;
            const res = await fetch(url);
            if (res.ok) {
                // Hier bekommst du z. B. ein Array von VorstellungStats
                const data = await res.json();
                // data = [{ vorstellungId: 1, filmTitel: "Hickel", totalRevenue: 30 }, ...]

                // Summiere totalRevenue
                let sum = 0;
                data.forEach((item) => {
                    sum += item.totalRevenue;
                });

                setEinnahmen(sum + " €");
            } else if (res.status === 404) {
                // Falls keine Stats vorhanden -> 0
                setEinnahmen("0 €");
            } else {
                console.error("Fehler beim Laden der Einnahmen:", res.status);
                setEinnahmen("Fehler");
            }
        } catch (error) {
            console.error("Fehler bei der Stats-Abfrage:", error);
            setEinnahmen("Fehler");
        }
    };

    return (
        <div className="dashboard-container">
            <h2>Film Dashboard</h2>

            <div className="dashboard-content">
                <label className="label">Film:</label>
                <select
                    className="select"
                    value={selectedFilm}
                    onChange={handleFilmChange}
                >
                    <option value="">Filme</option>
                    {filme.map((film, index) => (
                        <option key={index} value={film}>
                            {film}
                        </option>
                    ))}
                </select>

                <label className="label">Gesamteinnahmen:</label>
                <input
                    className="input"
                    type="text"
                    value={einnahmen}
                    readOnly
                />
            </div>
            <button className="button" onClick={handleGoToFilmPlanung}>
                Film-Dashboard
            </button>
        </div>
    );
};

export default FilmDashboard;
