import React, { useState } from "react";
import "../styles/Buchung.css"; // Import der allgemeinen CSS-Datei

const Reservierung = () => {
    const [reservierungsnummer] = useState(27698);
    const [buchungsnummer] = useState(54647);
    const [summe] = useState("50€");
    const [selectedZahlung, setSelectedZahlweise] = useState("");
    const [selectedFilm, setSelectedFilm] = useState("");
    const [selectedKategorie, setSelectedKategorie] = useState("");
    const [start] = useState("16:00");
    const [ende] = useState("17:30");
    const [anzahl, setAnzahl] = useState(1);
    const [freiePlaetze] = useState(16);

    const zahlweise = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];
    const film = ["FILM A", "FILM B", "FILM C"];
    const kategorien = ["PARKETT", "LOGE", "LOGE_SERVICE"];


    return (
        <div className="buchung-container-b">
            <h2>Tickets buchen</h2>

            <div className="form-grid-b">

                <label>Film:</label>
                <select value={selectedFilm} onChange={(e) => setSelectedFilm(e.target.value)}>
                    <option value="">Film</option>
                    {film.map((kat, index) => (
                        <option key={index} value={kat}>{kat}</option>
                    ))}
                </select>

                <label className="label-b">Start:</label>
                <input type="number" value={start} readOnly className="input-b"/>

                <label className="label-b">Ende:</label>
                <input type="number" value={ende} readOnly className="input-b"/>

                <label>Sitzplatzkategorie:</label>
                <select value={selectedKategorie} onChange={(e) => setSelectedKategorie(e.target.value)}>
                    <option value="">Sitzplatzkategorie</option>
                    {kategorien.map((kat, index) => (
                        <option key={index} value={kat}>{kat}</option>
                    ))}
                </select>

                <label className="label-b">Anzahl freie Plätze:</label>
                <input type="number" value={freiePlaetze} readOnly className="input-b"/>

                <label className="label-b">Anzahl:</label>
                <input type="number" value={anzahl} readOnly className="input-b"/>

                <label className="label-b">Summe:</label>
                <input type="number" value={summe} readOnly className="input-b"/>

                <label>Zahlweise:</label>
                <select value={selectedZahlung} onChange={(e) => setSelectedZahlweise(e.target.value)}>
                    <option value="">Zahlweise</option>
                    {zahlweise.map((kat, index) => (
                        <option key={index} value={kat}>{kat}</option>
                    ))}
                </select>

                <label className="label-d">Buchungsnummer:</label>
                <input type="text" value={buchungsnummer} readOnly className="input-b"/>

            </div>

            <div className="footer-b">
                <button className="button-b">Buchen</button>
            </div>
        </div>
    );
};

export default Reservierung;
