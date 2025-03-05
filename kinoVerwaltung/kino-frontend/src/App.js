import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Benutzer from "./pages/Benutzer";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Filmplanung from "./pages/Filmplanung";
import FilmDashboard from "./pages/FilmDashboard";
import SaalAnlegen from "./pages/SaalAnlegen";
import KundenDashboard from "./pages/KundenDashboard";
import TicketReservierung from "./pages/TicketReservierung";
import ReservierungDashboard from "./pages/ReservierungDashboard";
import Buchung from "./pages/Buchung";
import VorstellungAnlegen from "./pages/VorstellungAnlegen";

function App() {
  return (
      <Router>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/benutzer" element={<Benutzer />} />
            <Route path="/login" element={<Login />} />
            <Route path="/filmplanung" element={<Filmplanung />} />
            <Route path="/filmdashboard" element={<FilmDashboard />} />
            <Route path="/saalanlegen" element={<SaalAnlegen />} />
            <Route path="/kundendashboard" element={<KundenDashboard />} />
            <Route path="/ticketreservierung" element={<TicketReservierung />} />
            <Route path="/reservierungdashboard" element={<ReservierungDashboard />} />
            <Route path="/buchung" element={<Buchung />} />
            <Route path="/vorstellunganlegen" element={<VorstellungAnlegen />} />

        </Routes>
      </Router>
  );
}

export default App;
