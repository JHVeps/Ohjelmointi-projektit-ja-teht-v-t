import React, { useState } from "react";
import "./Dropdown.css";
import Rows from "../row/Rows";
import Requests from "../../Requests";
import AddFavorite from "../addFavorite/AddFavorite";

function Dropdown() {
  const [genres, setGenres] = useState("All genres");

  const categories = [
    "All genres",
    "Adventure",
    "Action",
    "Horror",
    "Comedy",
    "Animation",
    "Thriller",
  ];

  console.log(genres);

  return (
    <>
      <div className="dropdown">
        <div>
          <select
            className="dropdown-select"
            onChange={(e) => {
              setGenres(e.target.value);
            }}
          >
            {categories.map((category, id) => (
              <option className="dropdown-option" key={id}>
                {category}
              </option>
            ))}
          </select>
        </div>
      </div>
      {(genres === "Action" || genres === "All genres") && (
        <Rows
          title="Action Movies"
          fetcUrl={Requests.fetchAction}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
      {(genres === "Adventure" || genres === "All genres") && (
        <Rows
          title="Adventure Movies"
          fetcUrl={Requests.fetchAdventure}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
      {(genres === "Horror" || genres === "All genres") && (
        <Rows
          title="Horror Movies"
          fetcUrl={Requests.fetchHorror}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
      {(genres === "Comedy" || genres === "All genres") && (
        <Rows
          title="Comedy Movies"
          fetcUrl={Requests.fetchComedy}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
      {(genres === "Animation" || genres === "All genres") && (
        <Rows
          title="Animation"
          fetcUrl={Requests.fetchAnimation}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
      {(genres === "Thriller" || genres === "All genres") && (
        <Rows
          title="Thriller"
          fetcUrl={Requests.fetchThriller}
          isLargeRow
          favoriteComponent={AddFavorite}
        />
      )}
    </>
  );
}

export default Dropdown;
