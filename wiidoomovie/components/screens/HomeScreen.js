import React from "react";
import Nav from "../nav/Nav";
import Banner from "../banner/Banner";
import Footer from "../footer/Footer";
import Dropdown from "../dropdown/Dropdown";

function HomeScreen() {
  return (
    <div className="homeScreen">
      <Nav />
      <Banner />
      <Dropdown />
      <Footer />
    </div>
  );
}

export default HomeScreen;
