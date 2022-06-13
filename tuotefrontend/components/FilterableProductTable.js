import React, { useState } from "react";
import SearchBar from "./SearchBar";
import ProductTable from "./ProductTable";
import { AppContext } from "./AppContext";

const FilterableProductTable = (props) => {
  const [showAll, setAll] = useState(true);
  const showAllChanged = (status) => {
    setAll(status);
  };

  const [searchText, setSearchText] = useState("");
  const searchTextChanged = (searchMe) => {
    setSearchText(searchMe);
  };

  const themes = {
    light: {
      foreground: "#000000",
      background: "#eeeeee",
    },
    dark: {
      foreground: "#ffffff",
      background: "#222222",
    },
  };

  const PRODUCTS = [
    {
      category: "Sporting Goods",
      price: "$49.99",
      stocked: true,
      name: "Football",
    },
    {
      category: "Sporting Goods",
      price: "$9.99",
      stocked: true,
      name: "Baseball",
    },
    {
      category: "Sporting Goods",
      price: "$29.99",
      stocked: false,
      name: "Basketball",
    },
    {
      category: "Electronics",
      price: "$99.99",
      stocked: true,
      name: "iPod Touch",
    },
    {
      category: "Electronics",
      price: "$399.99",
      stocked: false,
      name: "iPhone 5",
    },
    {
      category: "Electronics",
      price: "$199.99",
      stocked: true,
      name: "Nexus 7",
    },
  ];

  return (
    <div>
      <AppContext.Provider value={themes.dark}>
        <h1>Tuotefrontend</h1>
        <SearchBar
          showAll={showAll}
          showAllChanged={showAllChanged}
          searchTextChanged={searchTextChanged}
        />
        <ProductTable
          showAll={showAll}
          products={PRODUCTS}
          searchText={searchText}
        />
      </AppContext.Provider>
    </div>
  );
};

export default FilterableProductTable;
