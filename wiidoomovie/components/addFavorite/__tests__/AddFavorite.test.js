import { render, within } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import AddFavorite from "../AddFavorite";

test("Renders correct elements", () => {
  const { getByRole, getByTestId } = render(<AddFavorite />);
  // Button is found
  expect(getByTestId("add-btn")).toBeInTheDocument();
  // Header inside button with text 'Add to Favorite
  expect(within(getByTestId("add-btn")).getByRole("heading")).toHaveTextContent(
    "Add to Favorite"
  );
  // Material ui button is found and has style color: black
  expect(getByTestId("AddCircleOutlineIcon")).toHaveStyle("color: Black");
});

test("Clicking 'Add to favorite' changes text to 'Added to favorite' and back", () => {
  const { getByRole, getByTestId } = render(<AddFavorite />);
  //Click Add to favorite button
  const addBtn = getByTestId("add-btn");
  userEvent.click(addBtn);
  // Add button is not in the document after clicking it
  expect(addBtn).not.toBeInTheDocument();

  // Delete button is rendered after click and has a header with text 'Added to Favorite'
  const deleteBtn = getByTestId("delete-btn");
  expect(deleteBtn).toBeInTheDocument();
  expect(within(deleteBtn).getByRole("heading")).toHaveTextContent(
    "Added to Favorite"
  );

  // Add button shows again after clicking the delete button
  userEvent.click(deleteBtn);
  expect(deleteBtn).not.toBeInTheDocument();
  expect(getByTestId("add-btn")).toBeInTheDocument();
});
