import React, { useEffect } from "react";
import "./App.css";
import HomeScreen from "./components/screens/HomeScreen";
import SignIn from "./components/screens/SignIn";
import ProfileScreen from "./components/screens/ProfileScreen";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ErrorPage from "./components/Error/ErrorPage";
import Play from "./components/play/Play";
import { auth } from "./firebase";
import { useDispatch, useSelector } from "react-redux";
import { login, logout, selectUser } from "./features/userSlice";
import MyFavoriteList from "./components/myFavoriteList/MyFavoriteList";

function App() {
  // const user = null;
  const user = useSelector(selectUser);
  const dispatch = useDispatch();

  useEffect(() => {
    const unsubscribe = auth.onAuthStateChanged((userAuht) => {
      if (userAuht) {
        //Logged in
        //console.log(userAuht);
        dispatch(
          login({
            uid: userAuht.uid,
            email: userAuht.email,
          })
        );
      } else {
        // Logged out
        dispatch(logout());
      }
    });
    // }, []);
    return unsubscribe;
  }, [dispatch]);

  return (
    <div className="app">
      <Router>
        {!user ? (
          <SignIn />
        ) : (
          <Routes>
            <Route path="/profile" element={<ProfileScreen />} />
            <Route exact path="/" element={<HomeScreen />} />
            <Route path="/play/:id" element={<Play />} />
            <Route path="/myfavorite" element={<MyFavoriteList />} />
            <Route path="*" element={<ErrorPage />} />
          </Routes>
        )}
      </Router>
    </div>
    // <Router>
    //   <Routes>
    //     <Route path="/" element={<HomeScreen />} />
    //     <Route path="*" element={<ErrorPage />} />
    //     <Route path="/play/:id" element={<Play />} />
    //   </Routes>
    // </Router>
  );
}

export default App;
