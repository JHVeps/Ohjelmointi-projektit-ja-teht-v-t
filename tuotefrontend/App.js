import React from "react";
import "./styles/index.css";
import OrderTable from "./components/OrderTable";
import FilterableProductTable from "./components/FilterableProductTable";
import Home from "./components/Home";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  NavLink,
} from "react-router-dom";

const App = () => {
  return (
    <div>
      <Router className="content">
        <main>
          <nav>
            <ul className="header">
              <li>
                <NavLink activeClassName="active" to="/">
                  Home
                </NavLink>
              </li>
              <li>
                <NavLink activeClassName="active" to="/FilterableProductTable">
                  Products
                </NavLink>
              </li>
              <li>
                <NavLink activeClassName="active" to="/OrderTable">
                  Orders
                </NavLink>
              </li>
            </ul>
          </nav>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route
              path="/FilterableProductTable"
              element={<FilterableProductTable />}
            />
            <Route path="/OrderTable" element={<OrderTable />} />
          </Routes>
        </main>
      </Router>
    </div>
  );
};

export default App;

{
  /* <Route path="/profile" element={<ProfileScreen />} />
  <div className="box1">
      <table className="box2">
        <tbody>
          <tr>
            <th>Name</th>
            <th>Price {props.searchText}</th>
          </tr>

          {props.products.map(
            (product) =>
              (props.searchText && product.name.search(props.searchText) < 0) ||
              ((props.showAll || product.stocked) && (
                <ProductRow product={product} key={product.name} />
              ))
          )}
        </tbody>
      </table>
              </div>*/
}
