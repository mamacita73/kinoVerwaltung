import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ReservierungZuBuchung.css";

const ReservierungZuBuchung = () => {
    const navigate = useNavigate();

    // State für Reservierungsnummer (vom Benutzer eingegeben)
    const [reservierungsnummer, setReservierungsnummer] = useState("");
    // Summe wird hier als Platzhalter angezeigt (Backend berechnet sie)
    const [summe, setSumme] = useState("");
    const [selectedZahlung, setSelectedZahlweise] = useState("");

    const zahlweise = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];

    // Abbrechen-Handler
    const handleAbbrechen = () => {
        navigate("/KundenDashboard"); // oder zu einer anderen Route
    };

    // Buchen-Handler: Sendet den RESERVIERUNG_BUCHEN-Command ans Backend
    const handleBuchen = async () => {
        if (!reservierungsnummer || !selectedZahlung) {
            alert("Bitte Reservierungsnummer und Zahlweise auswählen!");
            return;
        }

        // Command-Payload
        const payload = {
            command: "RESERVIERUNG_BUCHEN",
            payload: {
                reservierungId: parseInt(reservierungsnummer, 10),
                zahlweise: selectedZahlung,
            },
        };

        try {
            const response = await fetch("http://localhost:8080/buchung/reservierung", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });
            if (!response.ok) {
                throw new Error("Fehler bei der Umwandlung in eine Buchung");
            }
            const result = await response.json();
            alert("Buchung erfolgreich: " + result.message);
            // Optional: setze eventuell die Summe, falls vom Server zurückgegeben
            if (result.summe) setSumme(result.summe + "€");
        } catch (error) {
            console.error("Fehler:", error);
            alert("Fehler beim Buchen: " + error.message);
        }
    };

    return (
        <div className="reservierung-container-rb">
            <h2>Reservierung buchen</h2>
            <div className="form-grid-rb">
                <label className="label-rd">Reservierungsnummer:</label>
                <input
                    type="text"
                    value={reservierungsnummer}
                    onChange={(e) => setReservierungsnummer(e.target.value)}
                    className="input-rd"
                />

                <label className="label-rb">Summe:</label>
                <input type="text" value={summe} readOnly className="input-rd" />

                <label>Zahlweise:</label>
                <select
                    value={selectedZahlung}
                    onChange={(e) => setSelectedZahlweise(e.target.value)}
                >
                    <option value="">Zahlweise</option>
                    {zahlweise.map((z, index) => (
                        <option key={index} value={z}>
                            {z}
                        </option>
                    ))}
                </select>
            </div>

            <div className="footer-rb">
                <button className="button-rb" onClick={handleAbbrechen}>
                    Abbrechen
                </button>
                <button className="button-rb" onClick={handleBuchen}>
                    Buchen
                </button>
            </div>
        </div>
    );
};

export default ReservierungZuBuchung;
