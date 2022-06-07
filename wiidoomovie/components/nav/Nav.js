import React, { useEffect, useState } from "react";
import "./Nav.css";
import logo from "./logo.svg";
import SearchIcon from "@mui/icons-material/Search";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import ListIcon from "@mui/icons-material/List";

function Nav() {
  const history = useNavigate();

  const [searchFilm, setSearchFilm] = useState([]);
  const [fetchName, setFetchName] = useState([]);
  const [booleanTemp, setBooleanTemp] = useState("false");

  /**Fetch from TMDB by movie name */
  useEffect(() => {
    async function searchData() {
      const requestSearch = await axios.get(
        `https://api.themoviedb.org/3/search/movie?query=${fetchName}&api_key=${process.env.REACT_APP_API_KEY}&language=en-US&page=1&include_adult=false`
      );
      setSearchFilm(requestSearch.data.results);
    }
    booleanTemp === "true_name" && searchData();
  }, [fetchName, booleanTemp]);

  /** handleChange that handles change in searchbar. also search__result shall quide awesome list
   */
  const handleChange = (event) => {
    const moviename = event.target.value;
    setFetchName(moviename);
    if (moviename === "") setBooleanTemp("no_name");
    else setBooleanTemp("true_name");
  };

  /** emptySearchBar for emptying search field when clicked link */
  const emptySearchBar = (event) => {
    setBooleanTemp("no_name");
  };

  return (
    <>
      <div className="Navbar" data-testid="Navbar">
        <div className="logo">
          <Link to={`/`}>
            <img className="logoSize" src={logo} alt="WiiduuMovie Logo" />
          </Link>
        </div>

        <div className="search">
          <input type="text" placeholder="Search" onChange={handleChange} />

          {booleanTemp === "true_name" && (
            <div className="search-container">
              {searchFilm?.map((filmName) => (
                <div key={filmName?.id} className="search__result">
                  <Link
                    to={`/play/${filmName?.id}`}
                    style={{ textDecoration: "none" }}
                    onClick={emptySearchBar}
                  >
                    <div className="search__title">{filmName?.title} </div>
                  </Link>
                </div>
              ))}
            </div>
          )}
        </div>
        <div className="myFavorite">
          <Link
            to={`/myfavorite`}
            style={{ color: "black", textDecoration: "none" }}
          >
            <button>
              <ListIcon fontSize="large" />
            </button>
            <div className="favoriteText">My Favorite List</div>
          </Link>
        </div>
        <div className="signIn">
          <button onClick={() => history("/profile")}>
            <AccountCircleIcon fontSize="large" />
          </button>
        </div>
      </div>
    </>
  );
}

export default Nav;
