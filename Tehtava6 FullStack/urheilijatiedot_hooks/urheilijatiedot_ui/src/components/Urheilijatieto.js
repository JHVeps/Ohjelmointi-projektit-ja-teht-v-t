/* eslint-disable no-unused-vars */
import React from "react";
import { Link } from "react-router-dom";
import { useState, useContext } from "react";
import urheilijatiedotContext from "../context/UrheilijaTiedotContext";
import { useNavigate } from "react-router-dom";
const Urheilijatieto = (props) => {
  const UrheilijatiedotContext = useContext(urheilijatiedotContext); //hooks
  let history = useNavigate();
  const [naytaUrheilijatieto, setnaytaUrheilijatieto] = useState(false);
  const onDeleteClick = (id) => {
    UrheilijatiedotContext.poistaUrheilijatieto(id);
    window.location.reload();
    history("/");
  };
  const onShowClick = (e) => {
    let lippu = !naytaUrheilijatieto;
    setnaytaUrheilijatieto(lippu);
  };
  const { id, nimi, syntymavuosi, paino, kuva, laji, saavutukset } =
    props.urheilijatieto;
  return (
    <div className="card card-body mb-3 display:flex, justifyContent: flex-end">
      <div style={{ display: "flex", justifyContent: "flex-start" }}>
        <h4>{nimi}</h4>

        <button className="button_left" onClick={onShowClick.bind(this)}>
          Info
        </button>
      </div>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <div
          className="btn-group mr-2 text-right"
          role="group"
          aria-label="Second group"
        >
          <button
            className="button_right"
            onClick={onDeleteClick.bind(this, { id })}
          >
            Poista
          </button>
          <Link to={`urheilijatieto/muokkaa/${id}`}>
            <button className="button_right">Muokkaa</button>
          </Link>
        </div>
      </div>
      {naytaUrheilijatieto ? (
        <div className="container mt-3">
          <table className="table table-hover">
            <thead>
              <tr>
                <th>Syntymavuosi</th>
                <th>Paino</th>
                <th>Kuva linkki</th>
                <th>Laji</th>
                <th>Saavutukset</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{syntymavuosi}</td>
                <td>{paino}</td>
                <td>
                  <a href={kuva} target="_blank" rel="noreferrer">
                    Kuva
                  </a>
                </td>
                <td>{laji}</td>
                <td>{saavutukset}</td>
              </tr>
            </tbody>
          </table>
        </div>
      ) : null}
    </div>
  );
};

export default Urheilijatieto;
/* 
//Esimerkkinä saatu esitystapa
<ul className="list-group">
          <li className="list-group-item">Syntymavuosi: {syntymavuosi}</li>
          <li className="list-group-item">Paino: {paino}</li>
          <li className="list-group-item">
            <a href={kuva} target="_blank" rel="noreferrer">
              Kuva
            </a>
          </li>
          <li className="list-group-item">Laji: {laji}</li>
          <li  className="list-group-item">Saavutukset: {saavutukset}</li>
        </ul>*/
