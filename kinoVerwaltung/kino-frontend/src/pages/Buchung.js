import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Buchung.css";

const Buchung = () => {
    const navigate = useNavigate();

    // Liste aller Vorstellungen (Filme) aus dem Backend
    const [vorstellungen, setVorstellungen] = useState([]);

    // Formulareingaben
    const [selectedFilmId, setSelectedFilmId] = useState("");
    const [selectedFilm, setSelectedFilm] = useState(null); // Objekt der gewählten Vorstellung
    const [selectedKategorie, setSelectedKategorie] = useState("");
    const [anzahl, setAnzahl] = useState(1);
    const [selectedZahlung, setSelectedZahlweise] = useState("");
    const [summe, setSumme] = useState(0);
    const [buchungsnummer, setBuchungsnummer] = useState(""); // Wird ggf. vom Server zurückgegeben

    // Dummy-Werte bzw. statische Listen für Kategorien und Zahlweise
    const kategorien = ["PARKETT", "LOGE", "LOGE_SERVICE"];
    const zahlweise = ["EC-KARTE", "KREDIT-KARTE", "PAYPAL"];

    // Standardwert für freie Plätze, falls nicht dynamisch ermittelt
    const [freiePlaetze, setFreiePlaetze] = useState(20);

    // Beim Laden: Vorstellungen vom Backend abrufen
    useEffect(() => {
        fetch("http://localhost:8080/vorstellung")
            .then((res) => res.json())
            .then((data) => setVorstellungen(data))
            .catch((err) =>
                console.error("Fehler beim Laden der Vorstellungen:", err)
            );
    }, []);

    // Wenn ein Film ausgewählt wird, aktualisiere die Detailfelder
    useEffect(() => {
        if (selectedFilmId) {
            const film = vorstellungen.find((v) => v.id.toString() === selectedFilmId);
            setSelectedFilm(film || null);
            // Falls der Film ein Feld 'freiePlaetze' besitzt, können Sie diesen Wert verwenden.
            // Hier simulieren wir 20 freie Plätze, wenn kein Wert vorhanden ist.
            setFreiePlaetze(film && film.freiePlaetze ? film.freiePlaetze : 20);
        } else {
            setSelectedFilm(null);
            setFreiePlaetze(0);
        }
    }, [selectedFilmId, vorstellungen]);

    // Berechnung der Summe: Basis 7€ plus Aufschlag je Kategorie
    useEffect(() => {
        if (!selectedKategorie || !anzahl) {
            setSumme(0);
            return;
        }
        const base = 7;
        let surcharge = 0;
        switch (selectedKategorie.toUpperCase()) {
            case "PARKETT":
                surcharge = 1;
                break;
            case "LOGE":
                surcharge = 3;
                break;
            case "LOGE_SERVICE":
                surcharge = 5;
                break;
            default:
                surcharge = 0;
        }
        const seatPrice = base + surcharge;
        setSumme(seatPrice * anzahl);
    }, [selectedKategorie, anzahl]);

    // Handler für direkte Buchung
    const handleBuchen = async () => {
        if (!selectedFilmId || !selectedKategorie || !selectedZahlung) {
            alert("Bitte Film, Sitzplatzkategorie und Zahlweise auswählen!");
            return;
        }
        if (anzahl > freiePlaetze) {
            alert("Nicht genügend freie Plätze verfügbar!");
            return;
        }

        // Command-Payload für direkte Buchung (BUCHUNG_WRITE)
        const payload = {
            command: "BUCHUNG_WRITE",
            payload: {
                vorstellungId: parseInt(selectedFilmId, 10),
                kategorie: selectedKategorie,
                anzahl: anzahl,
                kundenEmail: localStorage.getItem("loggedInEmail") || "",
            },
        };

        try {
            const response = await fetch("http://localhost:8080/buchung/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Buchung");
            }
            const result = await response.json();
            alert("Buchung erfolgreich: " + result.message);
            if (result.buchungsnummer) {
                setBuchungsnummer(result.buchungsnummer);
            }
            // Optional: Nach erfolgreicher Buchung können Sie zu einer Bestätigungsseite navigieren.
        } catch (error) {
            console.error("Fehler:", error);
            alert("Fehler beim Buchen: " + error.message);
        }
    };

    // Handler, um zur Startseite o.Ä. zu navigieren
    const handleAbbrechen = () => {
        navigate("/");
    };

    return (
        <div className="buchung-container-b">
            <h2>Tickets buchen</h2>
            <div className="form-grid-b">
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

                <label className="label-b">Start:</label>
                <input type="text" value={selectedFilm ? selectedFilm.startzeit : ""} readOnly className="input-b" />

                <label className="label-b">Ende:</label>
                {/* Berechnen Sie das Ende anhand von Startzeit und Dauer, falls gewünscht */}
                <input type="text" value={selectedFilm ? calculateEnde(selectedFilm.startzeit, selectedFilm.dauerMinuten) : ""} readOnly className="input-b" />

                <label>Sitzplatzkategorie:</label>
                <select
                    value={selectedKategorie}
                    onChange={(e) => setSelectedKategorie(e.target.value)}
                >
                    <option value="">Kategorie wählen</option>
                    {kategorien.map((kat, index) => (
                        <option key={index} value={kat}>
                            {kat}
                        </option>
                    ))}
                </select>

                <label className="label-b">Anzahl freie Plätze:</label>
                <input type="number" value={freiePlaetze} readOnly className="input-b" />

                <label className="label-b">Anzahl:</label>
                <input
                    type="number"
                    value={anzahl}
                    onChange={(e) => setAnzahl(parseInt(e.target.value, 10))}
                    min={1}
                    className="input-b"
                />

                <label className="label-b">Summe (€):</label>
                <input type="number" value={summe} readOnly className="input-b" />

                <label>Zahlweise:</label>
                <select
                    value={selectedZahlung}
                    onChange={(e) => setSelectedZahlweise(e.target.value)}
                >
                    <option value="">Zahlweise wählen</option>
                    {zahlweise.map((z, index) => (
                        <option key={index} value={z}>
                            {z}
                        </option>
                    ))}
                </select>

                <label className="label-b">Buchungsnummer:</label>
                <input type="text" value={buchungsnummer} readOnly className="input-b" />
            </div>

            <div className="footer-b">
                <button className="button-b" onClick={handleAbbrechen}>
                    Abbrechen
                </button>
                <button className="button-b" onClick={handleBuchen}>
                    Buchen
                </button>
            </div>
        </div>
    );
};

// Hilfsfunktion, um das Endzeitfeld zu berechnen
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
