import { render, within } from "@testing-library/react";
import Footer from "../Footer";
import { MemoryRouter } from "react-router-dom";

test("Footer renders and has correct elements", () => {
  const { getByRole, getByTestId } = render(
    <MemoryRouter>
      <Footer />
    </MemoryRouter>
  );
  const elem = getByRole("heading", {
    name: /Copyright © 2022 Team 11 OHTU222 LTDNS20 Karelia UAS/i,
  });
  expect(elem).toBeInTheDocument();
  const view = getByTestId("footer");

  expect(
    within(view).getByRole("img", {
      name: /wiiduumovie logo/i,
    })
  ).toBeInTheDocument();

  expect(getByRole("link", { name: /about us/i })).toBeInTheDocument();
  expect(getByRole("link", { name: /contact us/i })).toBeInTheDocument();
  expect(getByRole("link", { name: /term of services/i })).toBeInTheDocument();
  expect(getByRole("link", { name: /faq/i })).toBeInTheDocument();
  expect(getByRole("link", { name: /privacy policy/i })).toBeInTheDocument();
});
