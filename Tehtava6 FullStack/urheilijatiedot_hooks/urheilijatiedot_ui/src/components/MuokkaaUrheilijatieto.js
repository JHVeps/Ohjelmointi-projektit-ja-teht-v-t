import React, { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from "axios";
import urheilijatiedotContext from "../context/UrheilijaTiedotContext";
const MuokkaaUrheilijatieto = () => {
  const [nimi, setNimi] = useState("");
  const [syntymavuosi, setSyntymavuosi] = useState("");
  const [paino, setPaino] = useState("");
  const [kuva, setKuva] = useState("");
  const [laji, setLaji] = useState("");
  const [saavutukset, setSaavutukset] = useState("");
  const UrheilijatiedotContext = useContext(urheilijatiedotContext); //hooks
  const { id } = useParams();
  let history = useNavigate();
  useEffect(() => {
    console.log("urheilijatiedot");
    axios.get(`http://localhost:3006/urheilija/${id}`).then((res) => {
      const urheilijatiedot = res.data;
      setNimi(urheilijatiedot.nimi);
      setSyntymavuosi(urheilijatiedot.syntymavuosi);
      setPaino(urheilijatiedot.paino);
      setKuva(urheilijatiedot.kuva);
      setLaji(urheilijatiedot.laji);
      setSaavutukset(urheilijatiedot.saavutukset);
    });
  }, [id]);
  const handleSubmit = async (e) => {
    const paivitettyUrheilijatieto = {
      nimi: nimi,
      syntymavuosi: syntymavuosi,
      paino: paino,
      kuva: kuva,
      laji: laji,
      saavutukset: saavutukset,
    };

    UrheilijatiedotContext.setUrheilijatieto(id, paivitettyUrheilijatieto);
    history("/");
  };
  const onChangeNimi = (e) => {
    setNimi(e.target.value);
  };
  const onChangeSyntymavuosi = (e) => {
    setSyntymavuosi(e.target.value);
  };
  const onChangePaino = (e) => {
    setPaino(e.target.value);
  };
  const onChangeKuva = (e) => {
    setKuva(e.target.value);
  };
  const onChangeLaji = (e) => {
    setLaji(e.target.value);
  };
  const onChangeSaavutukset = (e) => {
    setSaavutukset(e.target.value);
  };
  return (
    <div className="card mb-3">
      <div className="card-header">Muokkaa urheilijatieto</div>

      <div className="card-body">
        <form onSubmit={handleSubmit.bind(this)}>
          <div className="form-group">
            <label htmlFor="nimi">Nimi</label>
            <input
              type="text"
              name="nimi"
              className="form-control form-control-lg"
              placeholder="Syötä nimi..."
              value={nimi}
              onChange={onChangeNimi}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nimi">Syntymavuosi</label>
            <input
              type="text"
              name="syntymavuosi"
              className="form-control form-control-lg"
              placeholder="Syötä syntymävuosi..."
              value={syntymavuosi}
              onChange={onChangeSyntymavuosi}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nimi">Paino</label>
            <input
              type="text"
              name="paino"
              className="form-control form-control-lg"
              placeholder="Syötä paino..."
              value={paino}
              onChange={onChangePaino}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nimi">Kuva</label>
            <input
              type="text"
              name="kuva"
              className="form-control form-control-lg"
              placeholder="Syötä kuvan www- linkki..."
              value={kuva}
              onChange={onChangeKuva}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nimi">Laji</label>
            <input
              type="text"
              name="laji"
              className="form-control form-control-lg"
              placeholder="Syötä laji..."
              value={laji}
              onChange={onChangeLaji}
            />
          </div>
          <div className="form-group">
            <label htmlFor="nimi">Saavutukset</label>
            <input
              type="text"
              name="saavutukset"
              className="form-control form-control-lg"
              placeholder="Syötä saavutukset..."
              value={saavutukset}
              onChange={onChangeSaavutukset}
            />
          </div>
          <input
            type="submit"
            value="Muokkaa urheilijatieto"
            className="btn btn-light btn-block"
          />
        </form>
      </div>
    </div>
  );
};
export default MuokkaaUrheilijatieto;
