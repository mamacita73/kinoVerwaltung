import Login from "./Login";

function Home() {
    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Willkommen zur KinoVerwaltung</h1>
            <p>Bitte logge dich ein, um fortzufahren.</p>
            <Login />
        </div>
    );
}

export default Home;
