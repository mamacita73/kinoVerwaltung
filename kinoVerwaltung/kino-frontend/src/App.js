import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Benutzer from "./pages/Benutzer";
import Login from "./pages/Login";
import Home from "./pages/Home";

function App() {
  return (
      <Router>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/benutzer" element={<Benutzer />} />
            <Route path="/login" element={<Login />} />
        </Routes>
      </Router>
  );
}

export default App;
