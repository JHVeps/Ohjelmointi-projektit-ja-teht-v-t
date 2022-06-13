import React from "react";
import CategoryRow from "./CategoryRow";
import ProductRow from "./ProductRow";

const ProductTable = (props) => {
  let lastCategory = null;
  let tuoteTaulu = [];
  props.products.forEach((product) => {
    if (product.category !== lastCategory)
      tuoteTaulu.push(
        <CategoryRow category={product.category} key={product.category} />
      );

    tuoteTaulu.push(
      (props.searchText && product.name.search(props.searchText) < 0) ||
        ((props.showAll || product.stocked) && (
          <ProductRow product={product} key={product.name} />
        ))
    );

    lastCategory = product.category;
  });
  return (
    <div className="box1">
      <table className="box2">
        <tbody>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
          {tuoteTaulu}
        </tbody>
      </table>
    </div>
  );
};

export default ProductTable;
