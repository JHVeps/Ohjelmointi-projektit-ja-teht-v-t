import React, { useState } from "react";
import "./SignIn.css";
import SignupScreen from "./SignupScreen";
import logo from "../nav/logo.svg";

function SignIn() {
  const [signIn, setSingIn] = useState(false);

  return (
    <div className="loginScreen">
      <div className="loginScreen__background">
        <img className="loginScreen__logo" src={logo} alt="..." />
        {/* <button onClick={() => setSingIn(true)} className="loginSreen__button">
          Sign In
        </button> */}

        <div className="loginScreen__gradient" />
      </div>

      <div className="loginScreen__body">
        {signIn ? (
          <SignupScreen />
        ) : (
          <>
            <h1>Movies, Series and more.</h1>
            <h2>Watch anywhere.</h2>
            <h3>
              Ready to watch? Click below to create or restart your membership.
            </h3>
            <div className="loginScreen__input">
              <button
                onClick={() => setSingIn(true)}
                className="loginScreen__getStarted"
              >
                GET STARTED
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}

export default SignIn;
