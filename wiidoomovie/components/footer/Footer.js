import React from "react";
import "./Footer.css";
import { Link } from "react-router-dom";
import logo from "./logo.svg";
import themoviedb_logo from "./themoviedb_logo.svg";

function footer() {
  return (
    <div className="footer" data-testid="footer">
      <div className="footerContent">
        <Link className="links" to="/">
          About us
        </Link>

        <Link className="links" to="/">
          Contact us
        </Link>

        <Link className="links" to="/">
          Term of services
        </Link>

        <Link className="links" to="/">
          FAQ
        </Link>
        <Link className="links" to="/">
          Privacy policy
        </Link>
      </div>
      <div className="themovieDB_logo">
        <a href="https://www.themoviedb.org/" target="_blank">
          <img
            className="themovieDBLogoSize"
            src={themoviedb_logo}
            alt="themovieDB Logo"
          />{" "}
        </a>
      </div>
      <p className="logoText">
        This product uses the TMDB API but is not endorsed or certified by TMDB{" "}
      </p>
      <div className="footerLogo">
        <h6>Copyright &copy; 2022 Team 11 OHTU222 LTDNS20 Karelia UAS</h6>
        <img className="logoSize" src={logo} alt="WiiduuMovie Logo" />
      </div>
    </div>
  );
}

export default footer;
