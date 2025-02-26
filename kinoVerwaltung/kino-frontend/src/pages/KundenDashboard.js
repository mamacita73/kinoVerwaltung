import React from "react";
import "../styles/KundenDashboard.css"; // Import der CSS-Datei

const KundenDashboard = () => {
    return (
        <div className="dashboard-container">
            <h2>Ticketverwaltung</h2>

            <div className="button-container">
                <button className="button">Tickets reservieren</button>
                <button className="button">Reservierung anzeigen</button>
                <button className="button">Tickets buchen</button>
            </div>
        </div>
    );
};

export default KundenDashboard;
