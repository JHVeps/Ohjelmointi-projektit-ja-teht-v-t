import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./Play.css";
import Footer from "../footer/Footer";
import Nav from "../nav/Nav";
import AddFavorite from "../addFavorite/AddFavorite";

const Play = () => {
  const { id } = useParams();
  const [movieDetails, setMovieDetails] = useState();
  const [trailer, setTrailer] = useState();
  const [cast, setCast] = useState();
  console.log("play movie: ", id);

  /**Axios to recieve details about the movie from theMovieDatabase */
  useEffect(() => {
    async function getDetails() {
      const axiosDetails = await axios.get(
        `https://api.themoviedb.org/3/movie/${id}?api_key=${process.env.REACT_APP_API_KEY}`
      );
      setMovieDetails(axiosDetails.data);
    }
    getDetails();
  }, [id]);
  console.log("play movie details: ", movieDetails);

  /** Axios to recieve trailer videos from TMDB */
  useEffect(() => {
    async function getTrailer() {
      const axiosTrailer = await axios.get(
        `https://api.themoviedb.org/3/movie/${id}/videos?api_key=${process.env.REACT_APP_API_KEY}&language=en-US`
      );
      setTrailer(axiosTrailer.data.results[0]);
    }
    getTrailer();
  }, [id]);
  console.log("play trailer: ", trailer);

  /**Axios to recieve cast from TMDB */
  //https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US

  useEffect(() => {
    async function getCast() {
      const axiosCast = await axios.get(
        `https://api.themoviedb.org/3/movie/${id}/credits?api_key=${process.env.REACT_APP_API_KEY}&language=en-US`
      );
      setCast(axiosCast.data);
    }
    getCast();
  }, [id]);
  console.log("play cast: ", cast);

  return (
    <div className="play" data-testid="play">
      <Nav />
      <div className="play-trailer" data-testid="play-trailer">
        {trailer?.key && (
          <div className="iframe-container">
            <iframe
              className="iframe-screen"
              title="trailer from youtube"
              // width="853"
              // height="480"
              src={`https://www.youtube.com/embed/${trailer?.key}`}
              frameBorder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
              allowFullScreen
            />
          </div>
        )}
      </div>
      <div className="play-container" data-testid="play-container">
        <h1 className="play__title" data-testid="play__title">
          {movieDetails?.original_title || movieDetails?.title}
        </h1>

        <AddFavorite />

        <div className="genre-ranking-container">
          {movieDetails?.genres && (
            <h1 className="play__genre" data-testid="play__genre">
              {movieDetails?.genres[0]?.name}
            </h1>
          )}
          {movieDetails?.vote_average && (
            <h1 className="play__ranking" data-testid="play__ranking">
              {movieDetails?.vote_average} &#9733;
            </h1>
          )}
        </div>
        <h1 className="play__details" data-testid="play__details">
          {movieDetails?.overview}
        </h1>
      </div>
      <div className="play-cast" data-testid="play-cast">
        <h1 className="cast-header" data-testid="cast-header">
          Cast
        </h1>
        {cast?.cast.map((actor) => (
          <div className="cast-row" data-testid="cast-row" key={actor?.id}>
            <div className="play-cast__name" data-testid="play-cast__name">
              {actor?.name || actor?.original_name}
            </div>
            <div
              className="play-cast__character"
              data-testid="play-cast__character"
            >
              {actor?.character}
            </div>
          </div>
        ))}
      </div>
      <Footer />
    </div>
  );
};

export default Play;
