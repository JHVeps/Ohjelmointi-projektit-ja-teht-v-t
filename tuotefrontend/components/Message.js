import React from "react";
import "../styles/index.css";

const Message = (props) => {
  return (
    <div className={props.tyyli}>
      {props.children}
      <hr />
      <p>My message</p>
    </div>
  );
};

export default Message;
