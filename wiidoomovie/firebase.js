import { initializeApp } from "firebase/app";
// import { getFirestore } from "firebase/firestore";
import { getAuth } from "firebase/auth";

const app = initializeApp({
  apiKey: "",
  authDomain: "",
  projectId: "",
  storageBucket: "",
  messagingSenderId: "",
  appId: "",
});

// const db = getFirestore();
const auth = getAuth();

export { app, auth };
// export default db;

