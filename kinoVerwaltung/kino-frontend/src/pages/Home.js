import Login from "./Login";
import Register from "./Register";

function Home() {
    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Willkommen zur KinoVerwaltung</h1>

            <div style={{ display: "flex", justifyContent: "center", gap: "100px", marginTop: "20px" }}>
                <Login />
                <Register />
            </div>
        </div>
    );
}

export default Home;
