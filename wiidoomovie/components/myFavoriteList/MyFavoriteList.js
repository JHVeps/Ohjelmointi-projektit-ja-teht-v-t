import React, { useState, useEffect } from "react";
import { AgGridColumn, AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-material.css";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";
//import AddFavorite from "./AddFavorite";
import Nav from "../nav/Nav";

function MyFavorite() {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = () => {
    fetch("http://localhost:5000/favorites")
      .then((response) => response.json())
      .then((data) => addKeys(data))
      .catch((err) => console.error(err));
  };

  //Add keys to the favorite objects
  const addKeys = (data) => {
    const keys = Object.keys(data);
    const valueKeys = Object.values(data).map((item, index) =>
      Object.defineProperty(item, "id", { value: keys[index] })
    );
    setFavorites(valueKeys);
  };

  //Does not work yet

  // const deleteFavorite = (title) => {
  //   fetch("http://localhost:5000/favorites", {
  //     method: "DELETE",
  //   })
  //     .then((response) => fetchItems())
  //     .catch((err) => console.error(err));
  // };

  return (
    <div className="App">
      <Nav />
      <div>
        <h1>My Favorite List</h1>
      </div>

      <div
        className="ag-theme-material"
        style={{ height: 400, width: 1100, margin: "auto" }}
      >
        <AgGridReact rowData={favorites}>
          <AgGridColumn
            sortable={true}
            filter={true}
            field="title"
            width={300}
          />
          <AgGridColumn
            headerName=""
            field="title"
            width={90}
            cellRendererFramework={(params) => (
              <IconButton
                // onClick={() => deleteFavorite(params.value)}
                size="small"
                color="secondary"
              >
                <DeleteIcon />
              </IconButton>
            )}
          />
        </AgGridReact>
      </div>
    </div>
  );
}

export default MyFavorite;
