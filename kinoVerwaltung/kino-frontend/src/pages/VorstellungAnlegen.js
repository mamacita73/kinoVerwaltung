import React, { useState, useEffect } from "react";
import "../styles/VorstellungAnlegen.css"

const VorstellungAnlegen = () => {

    const [saele, setSaele] = useState([]);
    const [saalId, setSaalId] = useState("");
    const [filmTitel, setFilmTitel] = useState("");
    const [startzeit, setStartzeit] = useState("");
    const [dauerMinuten, setDauerMinuten] = useState(90);
    const [message, setMessage] = useState("");


    // Säle laden
    useEffect(() => {
        fetch("http://localhost:8080/saal")
            .then((res) => res.json())
            .then((data) => {
                console.log("Geladene Säle:", data);
                setSaele(data);
            })
            .catch((err) => console.error("Fehler beim Laden der Säle:", err));
    }, []);


    const handleSave = async () => {
        const payload = {
            saalId: parseInt(saalId, 10),
            filmTitel,
            startzeit,
            dauerMinuten,
        };

        console.log("Sende Vorstellung Payload:", payload);

        try {
            const response = await fetch("http://localhost:8080/vorstellung/anlegen", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });
            if (!response.ok) {
                throw new Error("Fehler beim Anlegen der Vorstellung");
            }
            const result = await response.json();
            console.log("Vorstellung erfolgreich angelegt:", result);
            // Erfolgsmeldung setzen
            setMessage("Vorstellung wurde erfolgreich angelegt!");
            // Formular zurücksetzen (optional)
            setFilmTitel("");
            setStartzeit("");
            setDauerMinuten(90);
            setSaalId("");
        } catch (error) {
            console.error("Fehler:", error);
            setMessage("Fehler beim Anlegen der Vorstellung: " + error.message);
        }
    };

return (
    <div className="vorstellung-container-v">
        <h2>Vorstellung anlegen</h2>
        <div className="vorstellung-form-v">
        <div className="form-row">
            <label>Saal wählen:</label>
            <select value={saalId} onChange={(e) => setSaalId(e.target.value)}>
                <option value="">-- Bitte wählen --</option>
                {saele.map((s) => (
                    <option key={s.id} value={s.id}>
                        {s.name} (ID={s.id})
                    </option>
                ))}
            </select>
        </div>

        <div className="form-row">
            <label>Filmtitel:</label>
            <input
                value={filmTitel}
                onChange={(e) => setFilmTitel(e.target.value)}
                placeholder="Name des Films"
            />
        </div>

        <div className="form-row">
            <label>Startzeit:</label>
            <input
                value={startzeit}
                onChange={(e) => setStartzeit(e.target.value)}
                placeholder="z.B. 16:00"
            />
        </div>

        <div className="form-row">
            <label>Filmdauer (Min.):</label>
            <input
                type="number"
                value={dauerMinuten}
                onChange={(e) => setDauerMinuten(parseInt(e.target.value, 10) || 0)}
            />
        </div>

        <button className="button-v" onClick={handleSave}>
            Speichern
        </button>
    </div>
    </div>
);
};

export default VorstellungAnlegen;