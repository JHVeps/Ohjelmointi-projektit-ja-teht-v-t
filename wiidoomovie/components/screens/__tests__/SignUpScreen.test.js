import { render } from "@testing-library/react";
import SignupScreen from "../SignupScreen";

test("SignUpScreen renders and has correct elements", () => {
  const { getByPlaceholderText, getByRole } = render(<SignupScreen />);
  expect(getByRole("img", { name: /logo/i })).toBeInTheDocument();
  expect(
    getByRole("heading", { name: /sign in or signup/i })
  ).toBeInTheDocument();
  expect(getByPlaceholderText(/email/i)).toBeInTheDocument();
  expect(getByPlaceholderText(/password/i)).toBeInTheDocument();
  expect(
    getByRole("heading", {
      name: /new to wiiduumovie\? click signup to register\./i,
    })
  ).toBeInTheDocument();
});
