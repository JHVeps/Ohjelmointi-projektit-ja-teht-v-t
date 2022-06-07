import { render } from "@testing-library/react";
import SignIn from "../SignIn";

test("SignIn renders and has right elements", () => {
  const { getByRole } = render(<SignIn />);

  expect(
    getByRole("heading", { name: /movies, series and more\./i })
  ).toBeInTheDocument();
  expect(
    getByRole("heading", { name: /watch anywhere\./i })
  ).toBeInTheDocument();
  expect(
    getByRole("heading", {
      name: /ready to watch\? click below to create or restart your membership\./i,
    })
  ).toBeInTheDocument();
  expect(getByRole("button", { name: /get started/i })).toBeInTheDocument();
  expect(getByRole("img", { name: /\.\.\./i })).toBeInTheDocument();
});
