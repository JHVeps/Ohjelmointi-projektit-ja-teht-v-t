import React, { useRef } from "react";
import {
  getAuth,
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
} from "firebase/auth";
import "./SignupScreen.css";
import logo from "../nav/logo.svg";

function SignupScreen() {
  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  //User registeration
  const register = (e) => {
    e.preventDefault();

    const auth = getAuth();
    createUserWithEmailAndPassword(
      auth,
      emailRef.current.value,
      passwordRef.current.value
    )
      .then((authUser) => {
        const user = authUser.user;
        console.log(user);
      })
      .catch((error) => {
        alert(error.message);
      });
  };
  //User sign in
  const signIn = (e) => {
    e.preventDefault();
    const auth = getAuth();
    signInWithEmailAndPassword(
      auth,
      emailRef.current.value,
      passwordRef.current.value
    )
      .then((authUser) => {
        const user = authUser.user;
        console.log(user);
      })
      .catch((error) => {
        alert(error.message);
      });
  };

  return (
    <div className="signupScreen">
      <img src={logo} alt="logo"></img>
      <form className="signupScreen__form">
        <h1>Sign In or SignUp</h1>
        <input ref={emailRef} placeholder="Email" type="email" />
        <input ref={passwordRef} placeholder="Password" type="password" />
        <div className="signupScreen__buttons">
          <button
            type="submit"
            onClick={signIn}
            className="signupScreen__SignIn"
            data-cy="sign-in"
          >
            Sign In
          </button>
          <button
            type="submit"
            onClick={register}
            className="signupScreen__SignUp"
            data-cy="sign-up"
          >
            SignUp
          </button>
        </div>
        <h4>
          <span className="signupScreen__gray">New to WiiDuuMovie?</span>

          <span className="signupScreen__gray"> Click </span>
          <span className="signupScreen__link" onClick={register}>
            SignUp
          </span>
          <span className="signupScreen__gray"> to register.</span>
        </h4>
      </form>
    </div>
  );
}

export default SignupScreen;
