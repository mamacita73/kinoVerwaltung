import React, { useState } from "react";
import "../styles/ReservierungDashboard.css"; // Import der allgemeinen CSS-Datei

const Reservierung = () => {
    const [reservierungen, setReservierungen] = useState([]);
    const [email, setEmail] = useState(() => localStorage.getItem("loggedInEmail") || "");

    useEffect(() => {
        if (!email) return;
        fetch(`http://localhost:8080/reservierung/byEmail/${email}`)
            .then((res) => res.json())
            .then((data) => setReservierungen(data))
            .catch((err) => console.error("Fehler beim Laden der Reservierungen:", err));
    }, [email]);

    return (
        <div className="reservierung-container-rd">
            <h2>Reservierung</h2>

            <div className="form-grid-rd">

                {reservierungen.map((res) => (
                    <div key={res.id} className="reservierung-item">
                        <p>Reservierungsnummer: {res.reservierungsnummer}</p>
                        <p>Anzahl: {res.anzahl}</p>
                        <p>Status: {res.status}</p>
                        <p>Vorstellung: {res.vorstellungId}</p>
                        <p>Start: {res.star}</p>
                        <p>Ende: {res.ended}</p>  {/* falsch, muss aus start und dauer berechnet werden */}
                        <p>Start: {res.star}</p>

                        {/* Hier können weitere Details wie Film, Startzeit etc. angezeigt werden */}

                    </div>
                ))}

            </div>

            <div className="footer-rd">
                <button className="button-rd">Stornieren</button>
                <button className="button-rd">Buchen</button>
            </div>
        </div>
    );
};

export default Reservierung;
