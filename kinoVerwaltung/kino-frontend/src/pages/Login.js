import { useState } from "react";
import { useNavigate } from "react-router-dom"; // React Router für Weiterleitung
import { loginUser } from "../services/api";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const result = await loginUser(email, password);

        if (result === "KUNDE") {
            navigate("/kundedashboard");
        } else if (result === "ADMIN") {
            navigate("/filmplanung");
        } else {
            setError(result.error || "Ein unbekannter Fehler ist aufgetreten");
        }
    };


            return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h2>Login</h2>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {message && <p style={{ color: "green" }}>{message}</p>}
            <form
                onSubmit={handleSubmit}
                style={{ display: "inline-block", textAlign: "left", padding: "20px", border: "1px solid #ccc", borderRadius: "8px", background: "#f9f9f9" }}>

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
                    style={{ width: "100%", padding: "10px", marginTop: "10px", backgroundColor: "#007BFF", color: "white", border: "none", borderRadius: "5px", cursor: "pointer" }}>
                    Anmelden
                </button>
            </form>
        </div>
    );
}

export default Login;
