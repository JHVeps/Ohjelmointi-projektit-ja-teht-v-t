import { cleanup, render } from "@testing-library/react";
import Nav from "../Nav";
import { MemoryRouter } from "react-router-dom";

// Cleanup after each test
afterEach(() => cleanup());

// Navbar renders on page
test("Nav renders", () => {
  const { getByTestId } = render(
    <MemoryRouter>
      <Nav />
    </MemoryRouter>
  );
  const nav = getByTestId("Navbar");
  expect(nav).toBeInTheDocument();
});

describe("Nav contains elements", () => {
  // Site logo and src parameter contains "logo.svg"
  test("WiiDuuMovie logo", () => {
    const { getByRole } = render(
      <MemoryRouter>
        <Nav />
      </MemoryRouter>
    );
    const img = getByRole("img");
    expect(img).toBeInTheDocument();
    expect(img.src).toMatch(/logo.svg/);
  });

  // Searchbox has input field, search-button and search-icon inside that button
  test("Search box", () => {
    const { getByPlaceholderText, getByTestId } = render(
      <MemoryRouter>
        <Nav />
      </MemoryRouter>
    );
    const search = getByPlaceholderText("Search");
    expect(search).toBeInTheDocument();
  });

  // Button that has text "SIGN IN"
  test("SignIn button", () => {
    const { getByTestId } = render(
      <MemoryRouter>
        <Nav />
      </MemoryRouter>
    );
    const button = getByTestId("AccountCircleIcon");
    expect(button).toBeInTheDocument();
  });
});
