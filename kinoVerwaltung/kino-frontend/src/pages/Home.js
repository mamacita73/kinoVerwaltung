import Login from "./Login";
import Register from "./Register";

function Home() {
    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Willkommen zur KinoVerwaltung</h1>
            <p>Bitte logge dich ein, um fortzufahren.</p>
            <Login />
            <Register />

        </div>
    );
}

export default Home;
