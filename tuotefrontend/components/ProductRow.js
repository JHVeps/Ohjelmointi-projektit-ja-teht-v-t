import React from "react";

const ProductRow = (props) => {
  return (
    <tr>
      <td className={props.product.stocked ? "" : "warning"}>
        {props.product.name}
      </td>
      <td>{props.product.price}</td>
    </tr>
  );
};

export default ProductRow;
