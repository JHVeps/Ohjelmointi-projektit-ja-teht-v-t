import React from "react";

const CategoryRow = (props) => {
  return (
    <tr>
      <th colSpan="2"> {props.category} </th>
    </tr>
  );
};

export default CategoryRow;
