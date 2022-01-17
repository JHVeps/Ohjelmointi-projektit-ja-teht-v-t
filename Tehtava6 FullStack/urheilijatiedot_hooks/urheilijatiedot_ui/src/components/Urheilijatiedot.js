/* eslint-disable react-hooks/exhaustive-deps */
import React, { useContext, useEffect } from "react";
import Urheilijatieto from "./Urheilijatieto";
import urheilijatiedotContext from "../context/UrheilijaTiedotContext";
const Urheilijatiedot = () => {
  const UrheilijatiedotContext = useContext(urheilijatiedotContext); //hooks
  console.log(UrheilijatiedotContext);
  useEffect(() => {
    UrheilijatiedotContext.getUrheilijatiedot();
    console.log(UrheilijatiedotContext);
  }, []);
  return (
    <>
      <h4 className="mb-2">
        <span className="text-dark">Urheilijatiedot</span>
      </h4>

      {UrheilijatiedotContext.urheilijatiedot.length
        ? UrheilijatiedotContext.urheilijatiedot.map((urheilijatieto) => (
            <Urheilijatieto
              key={urheilijatieto.id}
              urheilijatieto={urheilijatieto}
            />
          ))
        : null}
    </>
  );
};
export default Urheilijatiedot;
