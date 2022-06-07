import React, { useState, useEffect } from "react";
import instance from "../../../axios";
import requests from "../../../Requests";
import axios from "axios";

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
        setBannerMovie(resultTMDB.data.results[0]);
      } catch (error) {
        console.error(error);
      }
    }
    fetchBanner();
  }, []);

  useEffect(() => {
    async function fetchMovieDetails() {
      const betterDetails = await axios.get(
        `http://api.themoviedb.org/3/movie/${bannerMovie.id}?api_key=${process.env.REACT_APP_API_KEY}`
      );
      setBannerDetails(betterDetails.data);
    }
    bannerMovie.id && fetchMovieDetails(); //bannerMovie.id will prevent error happening, but gives Nested block is redundant
  }, [bannerMovie?.id]); //will fetch if  bannerMovie.id exists

  return (
    <div className="bannerX" data-testid="bannerX">
      {bannerMovie?.backdrop_path && (
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
          <div className="banner-info">
            <h1 className="banner-info_title">
              {bannerMovie?.title ||
                bannerMovie?.original_title ||
                bannerMovie?.name}
            </h1>
            <div className="banner-info-container">
              <h1 className="banner-info_date">
                {bannerDetails?.release_date ||
                  bannerMovie?.release_date ||
                  bannerMovie?.first_air_date}
              </h1>
              <h1 className="banner-info_rating">
                &#9733;
                {bannerMovie?.vote_average || bannerDetails?.vote_average}
              </h1>
              <h1 className="banner-info_runtime">
                {bannerDetails?.runtime}min
              </h1>
              {bannerDetails?.genres && (
                <h1 className="banner-info_genre">
                  {bannerDetails?.genres[0].name}
                </h1>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Banner;
