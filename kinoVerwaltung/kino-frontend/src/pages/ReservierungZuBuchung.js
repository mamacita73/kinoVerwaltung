import React, { useState } from "react";
import "../styles/ReservierungZuBuchung.css"; // Import der allgemeinen CSS-Datei

const Reservierung = () => {
    const [reservierungsnummer] = useState(27698);
    const [summe] = useState("50€");
    const [selectedZahlung, setSelectedZahlweise] = useState("");
    const zahlweise = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];


    return (
        <div className="reservierung-container-rb">
            <h2>Reservierung buchen</h2>

            <div className="form-grid-rb">
                <label className="label-rd">Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly className="input-rd"/>

                <label className="label-rb">Summe:</label>
                <input type="number" value={summe} readOnly className="input-rd"/>

                <label>Zahlweise:</label>
                <select value={selectedZahlung} onChange={(e) => setSelectedZahlweise(e.target.value)}>
                    <option value="">Zahlweise</option>
                    {zahlweise.map((kat, index) => (
                        <option key={index} value={kat}>{kat}</option>
                    ))}
                </select>
            </div>

            <div className="footer-rb">
                <button className="button-rb">Abbrechnen</button>
                <button className="button-rb">Buchen</button>
            </div>
        </div>
    );
};

export default Reservierung;
