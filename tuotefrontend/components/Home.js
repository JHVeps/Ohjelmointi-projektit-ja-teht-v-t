import React from "react";
import Message from "./Message";

const Home = () => {
  return (
    <div>
      <h1>Tämä on kotisivu</h1>
      <Message tyyli="normal">
        <h3>Eka toteutus</h3>
        <p>Tekstiä...</p>
      </Message>
      <Message tyyli="abnormal">
        <h5>Toinen kutsu</h5>
      </Message>
    </div>
  );
};

export default Home;
