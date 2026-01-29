// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup } from "firebase/auth";



const firebaseConfig = {
  apiKey: "AIzaSyAtVmxgkL5qKqcfWcKY4HgLZ64BYT7KNw8",
  authDomain: "siksha-97ebf.firebaseapp.com",
  projectId: "siksha-97ebf",
  storageBucket: "siksha-97ebf.firebasestorage.app",
  messagingSenderId: "974269930953",
  appId: "1:974269930953:web:e883285578e5cecd685c18",
  measurementId: "G-7V13XEYTEY"
};

const app = initializeApp(firebaseConfig);

// Initialize Firebase Auth and Providers
export const auth = getAuth(app);
export const googleProvider = new GoogleAuthProvider();

// ✅ Single clean Google Sign-In function
export const signInWithGoogle = async () => {
  try {
    const result = await signInWithPopup(auth, googleProvider);
    console.log("Google User:", result.user);
    return result.user;
  } catch (error) {
    console.error("Google sign-in error:", error);
    throw error;
  }
};