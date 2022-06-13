import React, { useContext } from "react";
import { AppContext } from "./AppContext";

const Checkbox = (props) => {
  const theme = useContext(AppContext);

  return (
    <div>
      <input type="checkbox" onChange={props.cbChanged} />
      <label>{props.label}</label>
      <br />
      <button style={{ background: theme.background, color: theme.foreground }}>
        I am styled by theme context
      </button>
    </div>
  );
};

export default Checkbox;
