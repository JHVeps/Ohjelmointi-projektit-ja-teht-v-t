import React from "react";
import { useSelector } from "react-redux";
import { selectUser } from "../../features/userSlice";
import { auth } from "../../firebase";
import Nav from "../nav/Nav";
import "./ProfileScreen.css";

function ProfileScreen() {
  const user = useSelector(selectUser);

  return (
    <div className="profileScreen">
      <Nav />
      <div className="profileScreen__body">
        <h1>Edit profile</h1>
        <div className="profileScreen__info">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/0/0b/Netflix-avatar.png"
            alt=""
          />
          <div className="profileScreen__details">
            <h2>{user.email}</h2>
            <div className="profileScreen__plans">
              <div>
                <input placeholder="Firstname" type="firstname" />
                <input placeholder="Lastname" type="lastname" />
                <button type="submit">Save</button>
              </div>
              <h3>Some info...</h3>

              <p></p>
              <button
                onClick={() => auth.signOut()}
                className="profileScreen__signOut"
              >
                SIGN OUT
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProfileScreen;
