import { cleanup, render, waitFor, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Rows from "../Rows";
import mockAxios from "axios";
import { MemoryRouter } from "react-router-dom";

// Props for <Rows />
const props = {
  title: "testiotsikko",
  fetcUrl: "testiurl",
  isLargeRow: false,
  favoriteComponent: () => <div>Favorite</div>,
};

// Mock axios call before every test
beforeEach(() => {
  mockAxios.get.mockResolvedValueOnce({
    data: {
      results: [
        { id: 1, poster_path: "poster-path", backdrop_path: "backdrop-path" },
      ],
    },
  });
});

// Cleanup and clear mocks after each test
afterEach(() => {
  cleanup;
  jest.clearAllMocks();
});

// await waitFor() is waiting for component to render after axios call

test("Axios.get gets called exactly once", async () => {
  const { getByRole } = render(
    <MemoryRouter>
      <Rows {...props} />
    </MemoryRouter>
  );
  const header = await waitFor(() => getByRole("heading"));
  expect(mockAxios.get).toBeCalledTimes(1);
});

test("Rows and header render after successful API call", async () => {
  const { getByRole } = render(
    <MemoryRouter>
      <Rows {...props} />
    </MemoryRouter>
  );
  const header = await waitFor(() => getByRole("heading"));
  const img = getByRole("img");
  expect(header.innerHTML).toBe("testiotsikko");
  expect(img).toBeInTheDocument();
});

test("Image source is 'backdrop'_path when isLargeRow = false", async () => {
  const { getByRole } = render(
    <MemoryRouter>
      <Rows {...props} />
    </MemoryRouter>
  );
  const img = await waitFor(() => getByRole("img"));
  expect(img.src).toMatch(/backdrop-path/);
});

test("Image source is 'poster_path' when isLargeRow = true", async () => {
  const newProps = {
    title: "testiotsikko",
    fetcUrl: "testiurl",
    isLargeRow: true,
    favoriteComponent: () => <div>Favorite</div>,
  };

  const { getByRole } = render(
    <MemoryRouter>
      <Rows {...newProps} />
    </MemoryRouter>
  );
  const img = await waitFor(() => getByRole("img"));
  expect(img.src).toMatch(/poster-path/);
});
