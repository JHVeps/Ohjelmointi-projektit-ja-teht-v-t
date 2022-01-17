import React from "react";
import { useNavigate } from "react-router-dom";
import { useState, useContext } from "react";
import urheilijatiedotContext from "../context/UrheilijaTiedotContext";

export default function LisaaUrheilijatieto() {
  let history = useNavigate();
  const [nimi, setNimi] = useState("");
  const [syntymavuosi, setSyntymavuosi] = useState("");
  const [paino, setPaino] = useState("");
  const [kuva, setKuva] = useState("");
  const [laji, setLaji] = useState("");
  const [saavutukset, setSaavutukset] = useState("");
  const UrheilijatiedotContext = useContext(urheilijatiedotContext); //hooks

  const handleSubmit = async (e) => {
    const uusiSyntymavuositieto = {
      nimi: nimi,
      syntymavuosi: syntymavuosi,
      paino: paino,
      kuva: kuva,
      laji: laji,
      saavutukset: saavutukset,
    };
    console.log(uusiSyntymavuositieto);
    UrheilijatiedotContext.setUrheilijatiedot(uusiSyntymavuositieto);
    history("/");
  };
  return (
    <div className="card mb-3">
      <div className="card-header">Lisää urheilija</div>
      <div className="card-body">
        <form onSubmit={handleSubmit.bind(this)}>
          <div className="form-group">
            <label htmlFor="nimi">Nimi</label>
            <input
              id="nimitieto"
              type="text"
              name="nimi"
              className="form-control form-control-lg"
              placeholder="Syötä nimi..."
              value={nimi}
              onChange={(event) => setNimi(event.target.value)}
            />
            <div className="invalid-feedback">Täytä nimi</div>
          </div>
          <div className="form-group">
            <label htmlFor="syntymavuosi">Syntymavuosi</label>
            <input
              id="syntymavuositieto"
              type="text"
              name="syntymavuosi"
              className="form-control form-control-lg"
              placeholder="Syötä syntymäpäivä (YYYY-MM-DD)..."
              value={syntymavuosi}
              onChange={(event) => setSyntymavuosi(event.target.value)}
            />
            <div className="invalid-feedback">Täytä syntymavuosi</div>
          </div>
          <div className="form-group">
            <label htmlFor="paino">Paino</label>
            <input
              id="painotieto"
              type="text"
              name="paino"
              className="form-control form-control-lg"
              placeholder="Syötä paino..."
              value={paino}
              onChange={(event) => setPaino(event.target.value)}
            />
            <div className="invalid-feedback">Täytä paino</div>
          </div>
          <div className="form-group">
            <label htmlFor="kuva">Kuva</label>
            <input
              id="kuvatieto"
              type="text"
              name="kuva"
              className="form-control form-control-lg"
              placeholder="Syötä kuva www sijainti..."
              value={kuva}
              onChange={(event) => setKuva(event.target.value)}
            />
            <div className="invalid-feedback">Täytä kuvan www- linkki</div>
          </div>
          <div className="form-group">
            <label htmlFor="laji">Laji</label>
            <input
              id="lajitieto"
              type="text"
              name="laji"
              className="form-control form-control-lg"
              placeholder="Syötä laji..."
              value={laji}
              onChange={(event) => setLaji(event.target.value)}
            />
            <div className="invalid-feedback">Täytä laji</div>
          </div>
          <div className="form-group">
            <label htmlFor="laji">Saavutukset</label>
            <input
              id="saavutuksetTieto"
              type="text"
              name="saavutukset"
              className="form-control form-control-lg"
              placeholder="Syötä saavutukset..."
              value={saavutukset}
              onChange={(event) => setSaavutukset(event.target.value)}
            />
            <div className="invalid-feedback">Täytä saavutukset</div>
          </div>
          <input
            type="submit"
            value="Lisää urheilijatieto"
            className="btn btn-light btn-block"
          />
        </form>
      </div>
    </div>
  );
}
