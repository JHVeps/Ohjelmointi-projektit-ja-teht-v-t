import { cleanup, render, waitFor } from "@testing-library/react";
import Banner from "../Banner";
import mockAxios from "axios";

// Mock Banner component
jest.mock("../Banner");

// Before each test, mock axios calls for movies
beforeEach(() => {
  // Mock first axios call return a movie array with one movie
  mockAxios.get.mockResolvedValueOnce({
    data: {
      results: [
        {
          adult: false,
          backdrop_path: "/cTTggc927lEPCMsWUsdugSj6wAY.jpg",
          genre_ids: [28, 12],
          id: 335787,
          media_type: "movie",
          original_language: "en",
          original_title: "Uncharted",
          overview:
            "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
          popularity: 1532.115,
          poster_path: "/tlZpSxYuBRoVJBOpUrPdQe9FmFq.jpg",
          release_date: "2022-02-10",
          title: "Uncharted",
          video: false,
          vote_average: 7.2,
          vote_count: 323,
        },
      ],
    },
  });

  // Mock second axios call to return selected movie (only movie in the array from the first axios call)
  mockAxios.get.mockResolvedValueOnce({
    adult: false,
    backdrop_path: "/cTTggc927lEPCMsWUsdugSj6wAY.jpg",
    belongs_to_collection: null,
    budget: 120000000,
    genres: [
      { id: 28, name: "Action" },
      { id: 12, name: "Adventure" },
    ],
    homepage: "https://www.unchartedmovie.com",
    id: 335787,
    imdb_id: "tt1464335",
    original_language: "en",
    original_title: "Uncharted",
    overview:
      "A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
    popularity: 1532.115,
    poster_path: "/tlZpSxYuBRoVJBOpUrPdQe9FmFq.jpg",
    production_companies: [
      {
        id: 5,
        logo_path: "/71BqEFAF4V3qjjMPCpLuyJFB9A.png",
        name: "Columbia Pictures",
        origin_country: "US",
      },
      {
        id: 507,
        logo_path: "/z7H707qUWigbjHnJDMfj6QITEpb.png",
        name: "Atlas Entertainment",
        origin_country: "US",
      },
      {
        id: 125281,
        logo_path: "/3hV8pyxzAJgEjiSYVv1WZ0ZYayp.png",
        name: "PlayStation Productions",
        origin_country: "US",
      },
      {
        id: 23217,
        logo_path: "/kXBZdQigEf6QiTLzo6TFLAa7jKD.png",
        name: "Naughty Dog",
        origin_country: "US",
      },
      {
        id: 14439,
        logo_path: null,
        name: "Arad Productions",
        origin_country: "US",
      },
    ],
    production_countries: [
      { iso_3166_1: "US", name: "United States of America" },
    ],
    release_date: "2022-02-10",
    revenue: 139000000,
    runtime: 116,
    spoken_languages: [
      { english_name: "English", iso_639_1: "en", name: "English" },
      { english_name: "Spanish", iso_639_1: "es", name: "Español" },
    ],
    status: "Released",
    tagline: "Fortune favors the bold.",
    title: "Uncharted",
    video: false,
    vote_average: 7.2,
    vote_count: 333,
  });
});

// Cleanup after each test
afterEach(() => {
  jest.clearAllMocks();
  cleanup();
});

// Tests that Banner component renders
test("Banner renders", async () => {
  // Render Banner
  const { getByTestId } = render(<Banner />);
  // Wait for mock API calls to finish so that "banner-card" gets rendered
  const elem = await waitFor(() => getByTestId("bannerX"));
  // Expect banner-card to be in the document after successful API call
  expect(elem).toBeInTheDocument();
  // Expect mockAxios calls to have been called 2 times
  expect(mockAxios.get).toHaveBeenCalledTimes(2);
});

// Tests that banner card div renders after successful API call
test("banner-card renders after axios call", async () => {
  // Render Banner component
  const { getByTestId } = render(<Banner />);
  // Wait for mock API calls to finish so that "banner-card" gets rendered
  const banner = await waitFor(() => getByTestId("banner-card"));
  // Expect banner-card to be in the document after successful API call
  expect(banner).toBeInTheDocument();
  // Expect mockAxios calls to have been called 2 times
  expect(mockAxios.get).toHaveBeenCalled();
  // Expect the two calls have been called with correct URL addresses
  expect(mockAxios.get).toHaveBeenNthCalledWith(
    1,
    "/trending/all/week?api_key=c58e784898e252757cead51270ca07fc&language=en-US"
  );
  expect(mockAxios.get).toHaveBeenNthCalledWith(
    2,
    "http://api.themoviedb.org/3/movie/335787?api_key=c58e784898e252757cead51270ca07fc"
  );
});
