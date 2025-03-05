import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Buchung.css";

const Buchung = () => {
    const navigate = useNavigate();

    const [vorstellungen, setVorstellungen] = useState([]);
    const [selectedFilmId, setSelectedFilmId] = useState("");
    const [selectedFilm, setSelectedFilm] = useState(null);
    const [selectedKategorie, setSelectedKategorie] = useState("");
    const [anzahl, setAnzahl] = useState(1);
    const [selectedZahlung, setSelectedZahlweise] = useState("");
    const [summe, setSumme] = useState(0);
    const [buchungsnummer, setBuchungsnummer] = useState("");
    const [freiePlaetze, setFreiePlaetze] = useState(20);
    const [kundenEmail, setKundenEmail] = useState(() => {
        return localStorage.getItem("loggedInEmail") || "";
    });

    //  Vorstellungen laden
    useEffect(() => {
        fetch("http://localhost:8080/vorstellung")
            .then(res => res.json())
            .then(data => {
                console.log("Empfangene Vorstellungen:", data);
                setVorstellungen(data);
            })
            .catch(err => console.error("Fehler beim Laden der Vorstellungen:", err));
    }, []);

    //  Kunden-E-Mail
    useEffect(() => {
        const email = localStorage.getItem("loggedInEmail") || "";
        setKundenEmail(email);
    }, []);

    //  Bei Auswahl eines Films -> Verfügbarkeits-Endpoint
    useEffect(() => {
        if (selectedFilmId) {
            const film = vorstellungen.find(v => v.id.toString() === selectedFilmId);
            console.log("Ausgewählter Film:", film);
            setSelectedFilm(film || null);

            const url = `http://localhost:8080/vorstellung/${selectedFilmId}/verfuegbar?kategorie=${selectedKategorie}`;
            console.log("Verfügbarkeitsabfrage:", url);

            fetch(url)
                .then(res => res.json())
                .then(data => {
                    console.log("VRPC-Antwort (freie Plätze):", data);
                    // data = { freiePlaetze: 12 }
                    const freie = data.freiePlaetze; // KEIN data.length!
                    console.log("Anzahl freie Plätze:", freie);
                    setFreiePlaetze(freie);
                })
                .catch(err => {
                    console.error("Fehler bei der RPC-Verfügbarkeitsabfrage:", err);
                    setFreiePlaetze(20); // Fallback
                 });
        } else {
            setSelectedFilm(null);
            setFreiePlaetze(0);
        }
    }, [selectedFilmId, vorstellungen, selectedKategorie]);

    //  Summe berechnen
    useEffect(() => {
        if (!selectedKategorie || !anzahl) {
            setSumme(0);
            return;
        }
        const base = 7;
        let surcharge = 0;
        switch (selectedKategorie.toUpperCase()) {
            case "PARKETT": surcharge = 1; break;
            case "LOGE": surcharge = 3; break;
            case "LOGE_SERVICE": surcharge = 5; break;
            default: surcharge = 0;
        }
        setSumme((base + surcharge) * anzahl);
    }, [selectedKategorie, anzahl]);

    //  Buchen
    const handleBuchen = async () => {
        if (!selectedFilmId || !selectedKategorie || !selectedZahlung) {
            alert("Bitte Film, Sitzplatzkategorie und Zahlweise auswählen!");
            return;
        }
        if (anzahl > freiePlaetze) {
            alert("Nicht genügend freie Plätze verfügbar!");
            return;
        }
        const payload = {
            command: "BUCHUNG_WRITE",
            payload: {
                vorstellungId: parseInt(selectedFilmId, 10),
                kategorie: selectedKategorie,
                anzahl: anzahl,
                kundenEmail: kundenEmail,
            },
        };
        console.log("Sende Buchungs-Payload:", payload);
        try {
            const response = await fetch("http://localhost:8080/buchung/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });
            const result = await response.json();
            console.log("Buchungsergebnis:", result);
            if (!response.ok) {
                throw new Error(result.error || "Fehler beim Senden des Buchungs-Commands");
            }
            alert("Buchung erfolgreich! Buchungsnummer: " + result.buchungsnummer);
            console.log("result: ", result.message);
            setBuchungsnummer(result.buchungsnummer);
        } catch (error) {
            console.error("Fehler:", error);
            alert("Fehler beim Buchen: " + error.message);
        }
    };

    // 6) Abbrechen
    const handleAbbrechen = () => {
        navigate("/KundenDashboard");
    };

    return (
        <div className="buchung-container-b">
            <h2>Tickets buchen</h2>
            <div className="form-grid-b">
                <label>Kunden-E-Mail:</label>
                <input type="email" value={kundenEmail} readOnly />

                <label>Film:</label>
                <select
                    value={selectedFilmId}
                    onChange={(e) => setSelectedFilmId(e.target.value)}
                >
                    <option value="">Film wählen</option>
                    {vorstellungen.map((v) => (
                        <option key={v.id} value={v.id}>
                            {v.filmTitel} (Start: {v.startzeit})
                        </option>
                    ))}
                </select>

                <label>Start:</label>
                <input
                    type="text"
                    value={selectedFilm ? selectedFilm.startzeit : ""}
                    readOnly
                />

                <label>Ende:</label>
                <input
                    type="text"
                    value={selectedFilm ? calculateEnde(selectedFilm.startzeit, selectedFilm.dauerMinuten) : ""}
                    readOnly
                />

                <label>Sitzplatzkategorie:</label>
                <select
                    value={selectedKategorie}
                    onChange={(e) => setSelectedKategorie(e.target.value)}
                >
                    <option value="">Kategorie wählen</option>
                    {["PARKETT", "LOGE", "LOGE_SERVICE"].map((kat) => (
                        <option key={kat} value={kat}>
                            {kat}
                        </option>
                    ))}
                </select>

                <label>Anzahl freie Plätze:</label>
                <input type="number" value={freiePlaetze} readOnly />

                <label>Anzahl:</label>
                <input
                    type="number"
                    min={1}
                    value={anzahl}
                    onChange={(e) => setAnzahl(parseInt(e.target.value, 10))}
                />

                <label>Summe (€):</label>
                <input type="number" value={summe} readOnly />

                <label>Zahlweise:</label>
                <select
                    value={selectedZahlung}
                    onChange={(e) => setSelectedZahlweise(e.target.value)}
                >
                    <option value="">Zahlweise wählen</option>
                    {["EC-KARTE", "KREDIT-KARTE", "PAYPAL"].map((z) => (
                        <option key={z} value={z}>
                            {z}
                        </option>
                    ))}
                </select>

                <label>Buchungsnummer:</label>
                <input type="text" value={buchungsnummer} readOnly />
            </div>

            <div className="footer-b">
                <button className="button-b" onClick={handleAbbrechen}>Zurück</button>
                <button className="button-b" onClick={handleBuchen}>Buchen</button>
            </div>
        </div>
    );
};

// Hilfsfunktion zur Berechnung des Endzeit-Felds
function calculateEnde(startzeit, dauerMinuten) {
    if (!startzeit || !dauerMinuten) return "";
    const [hours, minutes] = startzeit.split(":").map(Number);
    const start = new Date();
    start.setHours(hours);
    start.setMinutes(minutes);
    start.setMinutes(start.getMinutes() + parseInt(dauerMinuten, 10));
    const endHours = String(start.getHours()).padStart(2, "0");
    const endMinutes = String(start.getMinutes()).padStart(2, "0");
    return `${endHours}:${endMinutes}`;
}

export default Buchung;
