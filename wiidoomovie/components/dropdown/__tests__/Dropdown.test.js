import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Dropdown from "../Dropdown";

jest.mock("../Dropdown");

describe("Dropdown selections", () => {
  test("Select has 7 genres", () => {
    const { getByTestId } = render(<Dropdown />);
    const select = getByTestId("select");
    expect(select.children.length).toBe(7);
  });
  test("Correct component renders when genre is selected", () => {
    const { getByTestId } = render(<Dropdown />);
    const select = getByTestId("select");
    // Select 'Action' option from dropdown menu and check if component renders
    userEvent.selectOptions(
      select,
      screen.getByRole("option", { name: "Action" })
    );
    expect(getByTestId("Action")).toBeInTheDocument();

    userEvent.selectOptions(
      select,
      screen.getByRole("option", { name: "Animation" })
    );
    expect(getByTestId("Animation")).toBeInTheDocument();
  });
});
