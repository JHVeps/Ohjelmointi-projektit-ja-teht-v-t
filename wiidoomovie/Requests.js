/* MOVIE id for genres in TMDB
Action          28
Adventure       12
Animation       16
Comedy          35
Crime           80
Documentary     99
Drama           18
Family          10751
Fantasy         14
History         36
Horror          27
Music           10402
Mystery         9648
Romance         10749
Science Fiction 878
TV Movie        10770
Thriller        53
War             10752
Western         37 */

const requests = {
  fetchTrending: `/trending/all/week?api_key=${process.env.REACT_APP_API_KEY}&language=en-US`,
  fetchNetflixOriginals: `/discover/tv?api_key=${process.env.REACT_APP_API_KEY}&with_networks=213`,
  fetchTopRated: `/movie/top_rated?api_key=${process.env.REACT_APP_API_KEY}&language=en-US`,
  fetchAction: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=28 `,
  fetchAdventure: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=12 `,
  fetchAnimation: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=16 `,
  fetchComedy: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=35 `,
  fetchFantasy: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=14 `,
  fetchHorror: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=27`,
  fetchMystery: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=9648 `,
  fetchRomance: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=10749 `,
  fetchScienceFiction: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=878 `,
  fetchTvMovie: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=10770 `,
  fetchDocumentary: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=99`,
  fetchWar: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=10752`,
  fetchWestern: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=37`,
  fetchThriller: `/discover/movie?api_key=${process.env.REACT_APP_API_KEY}&with_genres=53`,
};

export default requests;
