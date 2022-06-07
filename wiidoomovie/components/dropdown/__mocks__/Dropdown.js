import React, { useState } from "react";
import "../Dropdown.css";

/* 
Mock dropdown
*/
function Dropdown() {
  const [genres, setGenres] = useState("");

  const categories = [
    "All genres",
    "Adventure",
    "Action",
    "Horror",
    "Comedy",
    "Animation",
    "Thriller",
  ];

  return (
    <>
      <div className="dropdown">
        <div>
          <select
            className="dropdown-select"
            onChange={(e) => {
              setGenres(e.target.value);
            }}
            data-testid="select"
          >
            {categories.map((category, id) => (
              <option
                className="dropdown-option"
                key={id}
                data-testid="options"
              >
                {category}
              </option>
            ))}
          </select>
        </div>
      </div>
      {(genres === "Action" || genres === "All genres") && (
        <div title="Action Movies" data-testid={genres} />
      )}
      {(genres === "Adventure" || genres === "All genres") && (
        <div title="Adventure Movies" data-testid={genres} />
      )}
      {(genres === "Horror" || genres === "All genres") && (
        <div title="Horror Movies" data-testid={genres} />
      )}
      {(genres === "Comedy" || genres === "All genres") && (
        <div title="Comedy Movies" data-testid={genres} />
      )}
      {(genres === "Animation" || genres === "All genres") && (
        <div title="Animation" data-testid={genres} />
      )}
      {(genres === "Thriller" || genres === "All genres") && (
        <div title="Thriller" data-testid={genres} />
      )}
    </>
  );
}

export default Dropdown;
