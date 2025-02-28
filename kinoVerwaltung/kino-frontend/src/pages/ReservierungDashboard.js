import React, { useState } from "react";
import "../styles/ReservierungDashboard.css"; // Import der allgemeinen CSS-Datei

const Reservierung = () => {
    const [reservierungsnummer] = useState(27698);
    const [anzahl] = useState(5);
    const [film] = useState("Herr der Stieben");
    const [start] = useState("16:00");
    const [ende] = useState("17:30");
    const [status] = useState("offen");

    return (
        <div className="reservierung-container-rd">
            <h2>Reservierung</h2>

            <div className="form-grid-rd">
                <label className="label-rd">Reservierungsnummer:</label>
                <input type="text" value={reservierungsnummer} readOnly className="input-rd"/>

                <label className="label-rd">Anzahl:</label>
                <input type="number" value={anzahl} readOnly className="input-rd"/>

                <label className="label-rd">Film:</label>
                <input type="text" value={film} readOnly className="input-rd"/>

                <label className="label-rd">Start:</label>
                <input type="text" value={start} readOnly className="input-rd"/>

                <label className="labe-rdl">Ende:</label>
                <input type="text" value={ende} readOnly className="input-rd"/>

                <label className="label-rd">Status:</label>
                <input type="text" value={status} readOnly className="input-rd"/>
            </div>

            <div className="footer-rd">
                <button className="button-rd">Stornieren</button>
                <button className="button-rd">Buchen</button>
            </div>
        </div>
    );
};

export default Reservierung;
