import React from "react";
import "../styles/KundenDashboard.css"; // Import der CSS-Datei

const KundenDashboard = () => {
    return (
        <div className="dashboard-container-kd">
            <h2>Ticketverwaltung</h2>

            <div className="button-container-kd">
                <button className="button-kd">Tickets reservieren</button>
                <button className="button-kd">Reservierung anzeigen</button>
                <button className="button-kd">Tickets buchen</button>
            </div>
        </div>
    );
};

export default KundenDashboard;
