// src/components/SignUpForm.jsx
import React, { useState } from "react";
import "./AuthForm.css";
import axios from "axios";
import { FaGoogle } from "react-icons/fa";
import { signInWithGoogle } from "../firebase"; // ✅ Correct import

const SignUpForm = ({ onLoginClick }) => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    mobileNumber: "",
  });
  const [error, setError] = useState("");

  // === Validation ===
  const validate = () => {
    const { name, email, mobileNumber } = formData;

    if (!name || !email || !mobileNumber)
      return "All fields are required.";

    const nameRegex = /^[A-Za-z\s]+$/;
    if (!nameRegex.test(name))
      return "Name must contain only alphabets.";

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) return "Invalid email format.";

    const mobileNumberRegex = /^[0-9]{10}$/;
    if (!mobileNumberRegex.test(mobileNumber))
      return "Phone number must be 10 digits.";

    return null;
  };

  // === Handle Input Change ===
  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  // === Handle Submit (Axios POST to Spring Boot) ===
  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationError = validate();
    if (validationError) {
      setError(validationError);
      return;
    }
    setError("");

    try {
      const response = await axios.post(
        "http://localhost:9191/api/users/register",
        formData
      );

      alert(response.data || "Registration successful!");
      setFormData({ name: "", email: "", mobileNumber: "" });
    } catch (error) {
      console.error("Error connecting to backend:", error);
      if (error.response) {
        alert(`Backend error: ${error.response.data}`);
      } else {
        alert("Failed to connect to backend. Please try again.");
      }
    }
  };

  // === Google Sign-Up ===
  const handleGoogleSignup = async () => {
    try {
      await signInWithGoogle(); // ✅ Use the helper function
    } catch (error) {
      console.error("Google signup error:", error);
      alert("Google sign-up failed. Please try again.");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="signup-form">
      <div>
        <img
          src="https://tradebrains-wp.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/shiksha.png"
          alt="Shiksha Logo"
          className="logo"
        />
      </div>
      <h2>Sign Up to Shiksha</h2>

      <input
        type="text"
        name="name"
        placeholder="Full Name"
        value={formData.name}
        onChange={handleChange}
      />

      <input
        type="email"
        name="email"
        placeholder="Email address"
        value={formData.email}
        onChange={handleChange}
      />

      <input
        type="tel"
        name="mobileNumber"
        placeholder="Phone number"
        value={formData.mobileNumber}
        onChange={handleChange}
      />

      {error && <div className="error">{error}</div>}

      <button type="submit">Register</button>

      <div className="social-login">
        <p>Or</p>
        <button
          type="button"
          className="google-btn"
          onClick={handleGoogleSignup}
        >
          <FaGoogle /> Continue with Google
        </button>
      </div>

      <p className="switch-text">
        Already have an account?{" "}
        <button type="button" className="link-btn" onClick={onLoginClick}>
          Login
        </button>
      </p>
    </form>
  );
};

export default SignUpForm;
