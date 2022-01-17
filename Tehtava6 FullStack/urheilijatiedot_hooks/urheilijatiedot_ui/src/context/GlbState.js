/* eslint-disable no-unused-vars */
import React, { useReducer } from "react";
import AppRdcer from "./AppRdcer";
import UrheilijaTiedotContext from "./UrheilijaTiedotContext";
import { GET_URHEILIJATIEDOT } from "./tyypit";
import axios from "axios";
const GlblState = (props) => {
  //initial state
  let initialState = {
    urheilijatiedot: [],
  };
  const [state, dispatch] = useReducer(AppRdcer, initialState);
  const getUrheilijatiedot = async () => {
    try {
      let res = await axios.get("http://localhost:3006/urheilija");
      let { data } = res;
      dispatch({ type: GET_URHEILIJATIEDOT, payload: data });
    } catch (error) {
      console.error(error);
    }
  };
  const getUrheilijatieto = async (id) => {
    try {
      let sql = "http://localhost:3006/urheilija/" + id;
      let res = await axios.get(sql);
      console.log("GET_URHEILJATIETO:");
      dispatch({ type: "GET_URHEILIJATIETO", payload: res.data });
      ///return res.data;
    } catch (error) {
      console.error(error);
    }
  };
  const setUrheilijatiedot = async (uusiUrheilijatieto) => {
    try {
      const res = await axios
        .post(`http://localhost:3006/lisaa`, uusiUrheilijatieto)
        .then((res) => {
          dispatch({ type: "ADD_URHEILIJATIETO", payload: res.data });
          console.log(res.data);
        });
    } catch (error) {
      console.error(error);
    }
  };
  const setUrheilijatieto = async (id, paivitettyUrheilijatieto) => {
    try {
      const res = await axios
        .put(`http://localhost:3006/urheilija/${id}`, paivitettyUrheilijatieto)
        .then((res) => {
          dispatch({ type: "EDIT_URHEILIJATIETO", payload: res.data });
          console.log(res.data);
        });
    } catch (error) {
      console.error(error);
    }
  };
  const poistaUrheilijatieto = async (id) => {
    try {
      let sql = "http://localhost:3006/urheilija/" + id["id"];

      const res = await axios.delete(sql).then((res) => {
        dispatch({ type: "DELETE_URHEILIJATIETO", payload: id["id"] });
        console.log(res.data);
      });
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <UrheilijaTiedotContext.Provider
      value={{
        urheilijatiedot: state.urheilijatiedot,
        getUrheilijatiedot,
        setUrheilijatieto,
        setUrheilijatiedot,
        poistaUrheilijatieto,
        getUrheilijatieto,
      }}
    >
      {props.children}
    </UrheilijaTiedotContext.Provider>
  );
};
//};
export default GlblState;
