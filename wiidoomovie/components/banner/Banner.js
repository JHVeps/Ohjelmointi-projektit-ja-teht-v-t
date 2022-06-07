import React, { useState, useEffect } from "react";
import "./Banner.css";
import instance from "../../axios";
import requests from "../../Requests";
import axios from "axios";
import { Link } from "react-router-dom";

const BANNER_BASEURL = "https://image.tmdb.org/t/p/original"; //baseurl for pictures, original has high-resolution pics, but one can use others than original
const Banner = () => {
  const [bannerMovie, setBannerMovie] = useState([]);
  const [bannerDetails, setBannerDetails] = useState([]);

  /**connection to TMDB-database
   instance has a baseURL from axios.js (basically instance is axios, but with baseURL) and end part of url comes from requests.js
    **/
  useEffect(() => {
    async function fetchBanner() {
      try {
        const resultTMDB = await instance.get(requests.fetchTrending);
        setBannerMovie(
          resultTMDB.data.results[
            Math.floor(Math.random() * resultTMDB.data.results.length - 1) //adds one movie from an array of 20 to setBannerMovie
          ]
        );
      } catch (error) {
        console.error(error);
      }
    }
    fetchBanner();
  }, []);
  // console.log("Banner requests genre:", bannerMovie);

  useEffect(() => {
    async function fetchMovieDetails() {
      const betterDetails = await axios.get(
        `https://api.themoviedb.org/3/movie/${bannerMovie.id}?api_key=${process.env.REACT_APP_API_KEY}`
      );
      setBannerDetails(betterDetails.data);
    }
    bannerMovie.id && fetchMovieDetails(); //bannerMovie.id will prevent error happening, but gives Nested block is redundant
  }, [bannerMovie?.id]); //will fetch if  bannerMovie.id exists

  // console.log("Banner more details", bannerDetails);
  return (
    <div className="bannerX" data-testid="bannerX">
      {bannerMovie?.backdrop_path && (
        <Link to={`/play/${bannerMovie.id}`} style={{ textDecoration: "none" }}>
          <div
            className="banner-card"
            data-testid="banner-card"
            style={{
              backgroundSize: "cover",
              backgroundImage: `url(
        "${BANNER_BASEURL}${
                bannerMovie?.backdrop_path || bannerMovie?.poster_path
              }"
    )`,
              backgroundRepeat: "no-repeat",
              backgroundPosition: "center center",
            }}
          >
            <div className="banner-info" data-testid="banner-info">
              <h1 className="banner-info_title" data-testid="banner-info_title">
                {bannerMovie?.title ||
                  bannerMovie?.original_title ||
                  bannerMovie?.name}
              </h1>

              <div
                className="banner-info-container"
                data-testid="banner-info-container"
              >
                <h1 className="banner-info_date" data-testid="banner-info_date">
                  {bannerMovie?.release_date ||
                    bannerMovie?.first_air_date ||
                    bannerDetails?.release_date}
                </h1>
                <h1
                  className="banner-info_rating"
                  data-testid="banner-info_rating"
                >
                  &#9733;
                  {bannerMovie?.vote_average || bannerDetails?.vote_average}
                </h1>
                {bannerDetails?.runtime && (
                  <h1
                    className="banner-info_runtime"
                    data-testid="banner-info_runtime"
                  >
                    {bannerDetails?.runtime}min
                  </h1>
                )}
                {bannerDetails?.genres && (
                  <h1
                    className="banner-info_genre"
                    data-testid="banner-info_genre"
                  >
                    {bannerDetails?.genres[0]?.name}
                  </h1>
                )}
              </div>
            </div>
          </div>
        </Link>
      )}
    </div>
  );
};

export default Banner;
