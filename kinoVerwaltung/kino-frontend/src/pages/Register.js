import { useState } from "react";
import { registerUser } from "../services/api";

function Register() {
    const [benutzername, setBenutzername] = useState("");
    const [email, setEmail] = useState("");
    const [passwort, setPasswort] = useState("");
    const [rolle, setRolle] = useState("USER"); // Standardrolle
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Einfache Validierung
        if (benutzername.length === 0 || email.length === 0 || passwort.length === 0) {
            setError("Alle Felder müssen ausgefüllt werden.");
            return;
        }

        if (passwort.length < 3) {
            setError("Das Passwort muss mindestens 6 Zeichen lang sein.");
            return;
        }

        const result = await registerUser(benutzername, email, passwort, rolle);

        if (result.id) {
            setMessage("Registrierung erfolgreich!");
            setError("");
        } else {
            setError("Fehler bei der Registrierung: " + (result.error || "Unbekannt"));
            setMessage("");
        }
    };

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h2>Registrierung</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {message && <p style={{ color: "green" }}>{message}</p>}
            <form onSubmit={handleSubmit} style={{ display: "inline-block", textAlign: "left", padding: "20px", border: "1px solid #ccc", borderRadius: "8px", background: "#f9f9f9" }}>
                <div>
                    <label>Benutzername:</label>
                    <input
                        type="text"
                        value={benutzername}
                        onChange={(e) => setBenutzername(e.target.value)}
                        required
                        style={{ display: "block", width: "100%", padding: "8px", margin: "5px 0" }}
                    />
                </div>
                <div>
                    <label>E-Mail:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{ display: "block", width: "100%", padding: "8px", margin: "5px 0" }}
                    />
                </div>
                <div>
                    <label>Passwort:</label>
                    <input
                        type="password"
                        value={passwort}
                        onChange={(e) => setPasswort(e.target.value)}
                        required
                        style={{ display: "block", width: "100%", padding: "8px", margin: "5px 0" }}
                    />
                </div>
                <div>
                    <label>Rolle:</label>
                    <select
                        value={rolle}
                        onChange={(e) => setRolle(e.target.value)}
                        style={{ display: "block", width: "100%", padding: "8px", margin: "5px 0" }}
                    >
                        <option value="USER">USER</option>
                        <option value="ADMIN">ADMIN</option>
                    </select>
                </div>
                <button type="submit" style={{ width: "100%", padding: "10px", marginTop: "10px", backgroundColor: "#28a745", color: "white", border: "none", borderRadius: "5px", cursor: "pointer" }}>
                    Registrieren
                </button>
            </form>
        </div>
    );
}

export default Register;
