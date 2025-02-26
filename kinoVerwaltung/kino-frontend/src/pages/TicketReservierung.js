/*import React, { useState } from "react";
import "../styles/TicketReservierung.css"; // Import der CSS-Datei

const TicketReservierung = () => {
    const [selectedFilm, setSelectedFilm] = useState("");
    const [selectedKategorie, setSelectedKategorie] = useState("");
    const [anzahl, setAnzahl] = useState(1);
    const [freiePlaetze, setFreiePlaetze] = useState(16);
    const [reservierungsnummer, setReservierungsnummer] = useState(27693);

    const filme = ["Film A", "Film B", "Film C"];
    const kategorien = ["PARKETT", "LOGE", "LOGE_SERVICE"];

    const handleReservierung = () => {
        alert(`Reservierung erfolgreich! Ihre Nummer: ${reservierungsnummer}`);
        setReservierungsnummer(reservierungsnummer + 1); // Erhöht Reservierungsnummer für nächste Buchung
    };

    return (
        <div className="reservierung-container">
            <h2>Tickets reservieren</h2>

            <div className="form-grid">
                <label>Film:</label>
                <select value={selectedFilm} onChange={(e) => setSelectedFilm(e.target.value)}>
                    <option value="">Filme</option>
                    {filme.map((film, index) => (
                        <option key={index} value={film}>{film}</option>
                    ))}
                </select>

                <label>Start:</label>
                <input type="text" value="16:00" readOnly />

                <label>Ende:</label>
                <input type="text" value="17:30" readOnly />

                <label>Sitzplatzkategorie:</label>
                <select value={selectedKategorie} onChange={(e) => setSelectedKategorie(e.target.value)}>
                    <option value="">Kategorie</option>
                    {kategorien.map((kat, index) => (
                        <option key={index} value={kat}>{kat}</option>
                    ))}
                </select>

                <label>Anzahl freie Plätze:</label>
                <input type="text" value={freiePlaetze} readOnly />

                <label>Anzahl:</label>
                <input type="number" value={anzahl} min="1" max={freiePlaetze} onChange={(e) => setAnzahl(parseInt(e.target.value) || 1)} />
            </div>

            <div className="reservierung-footer">
                <label>Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly />

                <button className="button" onClick={handleReservierung}>Reservieren</button>
            </div>
        </div>
    );
};

export default TicketReservierung;

 */
