import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Benutzer from "./pages/Benutzer";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Filmplanung from "./pages/Filmplannung";
import FilmDashboard from "./pages/FilmDashboard";
import SaalAnlegen from "./pages/SaalAnlegen";
import KundenDashboard from "./pages/KundenDashboard";
import TicketReservierung from "./pages/TicketReservierung";

function App() {
  return (
      <Router>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/benutzer" element={<Benutzer />} />
            <Route path="/login" element={<Login />} />
            <Route path="/filmplannung" element={<Filmplanung />} />
            <Route path="/filmdashboard" element={<FilmDashboard />} />
            <Route path="/saalanlegen" element={<SaalAnlegen />} />
            <Route path="/kundendashboard" element={<KundenDashboard />} />
            <Route path="/ticketreservierung" element={<TicketReservierung />} />
        </Routes>
      </Router>
  );
}

export default App;
