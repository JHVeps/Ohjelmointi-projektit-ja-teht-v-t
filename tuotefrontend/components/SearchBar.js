import React, { useState, useEffect } from "react";
import Checkbox from "./Checkbox";

const SearchBar = (props) => {
  const [isChecked, setChecked] = useState(props.showAll);

  const cbChanged = () => {
    setChecked(!isChecked);
  };

  useEffect(() => {
    props.showAllChanged(isChecked);
  });

  const stChanged = (e) => {
    props.searchTextChanged(e.target.value);
  };

  return (
    <div className="box1">
      <input
        type="search"
        placeholder="Search"
        onChange={(e) => stChanged(e)}
      />
      <br />

      <Checkbox label="Only show products in stock" cbChanged={cbChanged} />
    </div>
  );
};
export default SearchBar;
