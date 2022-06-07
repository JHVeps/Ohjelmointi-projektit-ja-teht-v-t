import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import Button from "@mui/material/Button";
import React, { useState } from "react";

function AddFavorite() {
  const [favorite, setFavorite] = useState(false);
  return (
    <>
      {favorite && (
        <Button
          onClick={() => {
            setFavorite(!favorite);
          }}
          style={{ color: "Black" }}
          data-testid="delete-btn"
        >
          <CheckCircleOutlineIcon
            style={{ color: "orangeRed" }}
            fontSize="large"
          />
          <h6>Added to Favorite </h6>
        </Button>
      )}

      {!favorite && (
        <Button
          onClick={() => {
            setFavorite(!favorite);
          }}
          aria-label="delete"
          style={{ color: "Black" }}
          data-testid="add-btn"
        >
          <h6>Add to Favorite</h6>
          <AddCircleOutlineIcon style={{ color: "Black" }} fontSize="large" />
        </Button>
      )}
    </>
  );
}

export default AddFavorite;
