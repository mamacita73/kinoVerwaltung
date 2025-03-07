import React from "react";
import "../styles/KundenDashboard.css"; // Import der CSS-Datei
import { useNavigate } from "react-router-dom";

const KundenDashboard = () => {

    const navigate = useNavigate();

    const handleZuReservieren = () => {
        navigate("/TicketReservierung");
    };

    const handleReservierung = () => {
        navigate("/ReservierungDashboard");
    };

    const handleBuchung = () => {
        navigate("/Buchung");
    };

    const handleBack = () => {
        navigate("/");
    };

    return (
        <div className="dashboard-container-kd">
            <h2>Ticketverwaltung</h2>

            <div className="button-container-kd">
                <button className="button-kd" onClick={handleZuReservieren}>Tickets reservieren</button>
                <button className="button-kd" onClick={handleReservierung}>Reservierung anzeigen</button>
                <button className="button-kd" onClick={handleBuchung}>Tickets buchen</button>
                <button className="button-kd" onClick={handleBack}>Abmelden</button>
            </div>
        </div>
    );
};

export default KundenDashboard;
