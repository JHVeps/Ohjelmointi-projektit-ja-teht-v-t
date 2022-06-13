import React, { useState, useEffect } from "react";
import axios from "axios";

function OrderTable() {
  const [orders, setOrders] = useState([]);

  const getOrders = () => {
    axios.get("http://localhost:3001/orders").then((response) => {
      console.log("promise fulfilled: ", response.data);
      console.log(response.data);
      setOrders(response.data);
    });
  };

  useEffect(getOrders, []);

  return (
    <div>
      <table>
        <tbody>
          <tr>
            <th>id</th>
            <th>orderdate</th>
            <th>client</th>
            <th>productname</th>
            <th>pcs</th>
            <th></th>
          </tr>
          {orders.map((order) => (
            <tr key={order.id}>
              <td>{order.id}</td>
              <td>{order.orderdate}</td>
              <td>{order.client}</td>
              <td>{order.productname}</td>
              <td>{order.pcs}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default OrderTable;
