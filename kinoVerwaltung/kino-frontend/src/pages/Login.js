import { useState } from "react";
import { useNavigate } from "react-router-dom"; // React Router für Weiterleitung

function Login() {
    const [email, setEmail] = useState(""); // useState für die E-Mail hinzugefügt
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [message, setMessage] = useState(""); // Falls später Erfolgsmeldungen benötigt werden
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Gesendete E-Mail:", email);


        try {
            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email }) // Dynamische E-Mail wird gesendet
            });

            const result = await response.json();



            if (result.success === "true") {
                setMessage("Login erfolgreich!");

                if (result.rolle === "ADMIN") {
                    navigate("/Filmplanung");
                } else {
                    navigate("/KundenDashboard");
                }
            } else {
                setError("E-Mail nicht gefunden!");
            }
        } catch (error) {
            setError("Es gab ein Problem mit der Anmeldung. Bitte versuche es erneut.");
        }
    };

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h2>Login</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {message && <p style={{ color: "green" }}>{message}</p>}
            <form
                onSubmit={handleSubmit}
                style={{
                    display: "inline-block",
                    textAlign: "left",
                    padding: "20px",
                    border: "1px solid #ccc",
                    borderRadius: "8px",
                    background: "#f9f9f9",
                }}
            >
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
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ display: "block", width: "100%", padding: "8px", margin: "5px 0" }}
                    />
                </div>
                <button
                    type="submit"
                    style={{
                        width: "100%",
                        padding: "10px",
                        marginTop: "10px",
                        backgroundColor: "#007BFF",
                        color: "white",
                        border: "none",
                        borderRadius: "5px",
                        cursor: "pointer",
                    }}
                >
                    Anmelden
                </button>
            </form>
        </div>
    );
}

export default Login;
