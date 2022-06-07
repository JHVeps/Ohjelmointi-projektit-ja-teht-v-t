import { cleanup, render, waitFor } from "@testing-library/react";
import mockAxios from "axios";
import Play from "../Play";

jest.mock("../Play");

// Mock 3 axios calls
beforeEach(() => {
  mockAxios.get.mockResolvedValueOnce({
    data: {
      adult: false,
      backdrop_path: "/fOy2Jurz9k6RnJnMUMRDAgBwru2.jpg",
      belongs_to_collection: null,
      budget: 190000000,
      genres: [{ id: 16, name: "Animation" }],
      homepage: "https://www.disneyplus.com/movies/turning-red/4mFPCXJi7N2m",
      id: 508947,
      imdb_id: "tt8097030",
      original_language: "en",
      original_title: "Turning Red",
      overview:
        "Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist – when she gets too excited, she transforms into a giant red panda.",
      popularity: 1994.835,
      poster_path: "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg",
      production_companies: [
        {
          id: 2,
          logo_path: "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
          name: "Walt Disney Pictures",
          origin_country: "US",
        },
      ],
      production_countries: [
        { iso_3166_1: "US", name: "United States of America" },
      ],
      release_date: "2022-03-10",
      revenue: 0,
      runtime: 100,
      spoken_languages: [
        { english_name: "English", iso_639_1: "en", name: "English" },
      ],
      status: "Released",
      tagline: "Growing up is a beast.",
      title: "Turning Red",
      video: false,
      vote_average: 7.5,
      vote_count: 305,
    },
  });

  mockAxios.get.mockResolvedValueOnce({
    data: {
      results: [
        {
          id: "62228261902012006d4df0b3",
          iso_639_1: "en",
          iso_3166_1: "US",
          key: "3U7KaI_NPGg",
          name: "“I’m Meilin Lee” Clip",
          official: true,
          published_at: "2022-03-04T18:00:02.000Z",
          site: "YouTube",
          size: 1080,
          type: "Clip",
        },
      ],
    },
  });

  mockAxios.get.mockResolvedValueOnce({
    data: {
      cast: [
        {
          adult: false,
          cast_id: 6,
          character: "Meilin 'Mei' Lee (voice)",
          credit_id: "60ed8ac0ab1bc7005e36338b",
          gender: 1,
          id: 3156344,
          known_for_department: "Acting",
          name: "Rosalie Chiang",
          order: 0,
          original_name: "Rosalie Chiang",
          popularity: 12.922,
          profile_path: "/zoxWGwCh9Ubbnaxmr14fPVafcPW.jpg",
        },
      ],

      crew: [
        {
          adult: false,
          credit_id: "622b2f98498ef9001a413ca4",
          department: "Sound",
          gender: 2,
          id: 7763,
          job: "Sound Designer",
          known_for_department: "Sound",
          name: "Ren Klyce",
          original_name: "Ren Klyce",
          popularity: 5.18,
          profile_path: "/tMDHEVa05pe3od1NMpfplPVPnxD.jpg",
        },
      ],

      id: 508947,
    },
  });
});

afterEach(() => cleanup());

// Test that page shows correct movie data
test("Page shows movie data", async () => {
  const { getByTestId } = render(<Play />);
  await waitFor(() => expect(mockAxios.get).toBeCalledTimes(3));
  const title = getByTestId("play__title");
  const genre = getByTestId("play__genre");
  const ranking = getByTestId("play__ranking");
  const details = getByTestId("play__details");
  const cast = getByTestId("play-cast__name");
  const character = getByTestId("play-cast__character");

  expect(title).toHaveTextContent("Turning Red");
  expect(genre).toHaveTextContent("Animation");
  expect(ranking).toHaveTextContent("7.5");
  expect(details.innerHTML).toMatch(/Thirteen-year-old Mei/);
  expect(cast).toHaveTextContent("Rosalie Chiang");
  expect(character).toHaveTextContent("Meilin 'Mei' Lee (voice)");
});
