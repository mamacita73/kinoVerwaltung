import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Benutzer from "./pages/Benutzer";

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<h1>Willkommen zur KinoVerwaltung</h1>} />
          <Route path="/benutzer" element={<Benutzer />} />
        </Routes>
      </Router>
  );
}

export default App;
