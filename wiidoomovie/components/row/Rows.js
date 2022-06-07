import React, { useState, useEffect } from "react";
import axios from "../../axios.js";
import "./Rows.css";
import { Link } from "react-router-dom";

function Rows({ title, fetcUrl, isLargeRow = false, favoriteComponent }) {
  const [movies, setMovies] = useState([]);

  const Favorite = favoriteComponent;

  const base_url = "https://image.tmdb.org/t/p/original/";

  useEffect(() => {
    async function fetchPosterData() {
      const request = await axios.get(fetcUrl);
      setMovies(request.data.results);
      return request;
    }

    fetchPosterData();
  }, [fetcUrl]);

  // Get link to the movie
  useEffect(() => {
    async function fetchMovieDetails() {
      await axios.get(
        `https://api.themoviedb.org/3/movie/${movies.id}?api_key=${process.env.REACT_APP_API_KEY}`
      );
    }
    movies.id && fetchMovieDetails();
  }, [movies?.id]);

  return (
    <div className="row" data-testid="row">
      <h2>{title}</h2>

      <div className="row__posters" data-testid="row__posters">
        {movies.map(
          (movie, index) =>
            ((isLargeRow && movie.poster_path) ||
              (!isLargeRow && movie.backdrop_path)) && (
              <div
                key={index}
                className="contents"
                data-testid={`contents-${index}`}
              >
                <Link
                  to={`/play/${movie.id}`}
                  style={{ color: "black", textDecoration: "none" }}
                >
                  <img
                    className={`row__poster ${
                      isLargeRow && "row__posterLarge"
                    }`}
                    key={movie.id}
                    src={`${base_url}${
                      isLargeRow ? movie.poster_path : movie.backdrop_path
                    }`}
                    alt={movie.name}
                  />

                  <p className="row__movieTitle">
                    {movie?.title || movie?.name || movie?.original_name}
                  </p>
                </Link>
                <div className="overlay">
                  <Favorite />
                </div>
              </div>
            )
        )}
      </div>
    </div>
  );
}

export default Rows;
