import React, { useState, useEffect } from "react";
import "../styles/ReservierungDashboard.css";
import {useNavigate} from "react-router-dom"; // Import der allgemeinen CSS-Datei

const ReservierungDashboard = () => {
    const [message, setMessage] = useState("");
    const [reservierungen, setReservierungen] = useState([]);
    const [email, setEmail] = useState(() => localStorage.getItem("loggedInEmail") || "");
    const navigate = useNavigate();
    const handleBack = () => {
        navigate("/KundenDashboard");
    };



    useEffect(() => {
        if (!email) return;

        fetch(`http://localhost:8080/reservierung/byEmail/${email}`)
            .then((res) => res.json())
            .then((data) => {
                console.log("Geladene Reservierungen:", data);
                const realData = JSON.parse(data);
                setReservierungen(realData);
            })
            .catch((err) => console.error("Fehler beim Laden der Reservierungen:", err));
    }, [email]);

    // Funktion zum Stornieren einer Reservierung
    const handleCancel = async (reservierungId) => {
        try {
            const response = await fetch(`http://localhost:8080/reservierung/${reservierungId}/cancel`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            if (!response.ok) {
                throw new Error("Fehler beim Senden des Stornierungs-Commands");
            }

            const result = await response.text();
            setMessage(result);

            // Reservierungen neu laden, damit die UI aktuell bleibt
            const updatedResponse = await fetch(`http://localhost:8080/reservierung/byEmail/${email}`);
            const updatedData = await updatedResponse.json();
            setReservierungen(updatedData);

        } catch (error) {
            console.error("Stornierungsfehler:", error);
            setMessage("Fehler beim Stornieren: " + error.message);
        }
    };

    return (
        <div className="reservierung-container-rd">
            <h2>Reservierung</h2>

            <div className="form-grid-rd">

                    <div key={resItem.id} className="reservierung-item">
                        <label className="label-rd">
                            Reservierungsnummer: {resItem.reservierungsnummer}
                        </label>
                        <label className="label-rd">
                            reservierte Sitze: {resItem.anzahl /* falls du anzahl als Feld hast */}
                        </label>
                        <label className="label-rd">
                            Sitzkategorie: {resItem.sitzKategorie /* falls vorhanden */}
                        </label>
                        <label className="label-rd">
                            Film: {resItem.filmTitel /* falls vorhanden */}
                        </label>
                        <label className="label-rd">
                            Start: {resItem.startzeit /* falls vorhanden */}
                        </label>
                        <label className="label-rd">
                            Ende: {resItem.endzeit /* oder berechnet: startzeit + dauer */}
                        </label>
                        <label className="label-rd">Status: {resItem.status}</label>

                        <button className="button-rd" onClick={() => handleCancel(resItem.id)}>
                            Stornieren
                        </button>
                        <button className="button-rd">Buchen</button>
                        <button className="button-tr" onClick={handleBack}>
                            Stornieren
                        </button>
                        <button className="button-tr" onClick={handleBack}>
                            Zurück
                        </button>
                    </div>

            </div>

            {message && <p>{message}</p>}
        </div>
    );
};

export default ReservierungDashboard;
