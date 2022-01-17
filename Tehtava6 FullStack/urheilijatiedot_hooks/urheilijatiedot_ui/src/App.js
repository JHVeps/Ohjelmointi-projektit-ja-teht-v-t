import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Ylatunniste from "./components/Ylatunniste";
import Footer from "./components/Footer";
import Tietoa from "./pages/Tietoa";
import Urheilijatiedot from "./components/Urheilijatiedot";
import LisaaUrheilijatieto from "./components/LisaaUrheilijatieto";
import MuokkaaUrheilijatieto from "./components/MuokkaaUrheilijatieto";
import "bootstrap/dist/css/bootstrap.min.css";
import GlbState from "./context/GlbState";

function App() {
  return (
    <GlbState>
      <Router>
        <div className="App">
          <Ylatunniste turvataso="keskisuuri" />
          <div className="container">
            <Routes>
              <Route path="/" element={<Urheilijatiedot />} />
              <Route
                path="/urheilijatieto/lisaa"
                element={<LisaaUrheilijatieto />}
              />
              <Route
                path="/urheilijatieto/muokkaa/:id"
                element={<MuokkaaUrheilijatieto />}
              />
              <Route path="/tietoa" element={<Tietoa />} />
            </Routes>
          </div>
          <Footer />
        </div>
      </Router>
    </GlbState>
  );
}

export default App;
