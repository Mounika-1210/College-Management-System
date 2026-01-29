import React, { useState } from "react";
import axios from "axios";
import { signInWithGoogle } from "../firebase";
import "./AuthForm.css";

const LoginForm = ({ onSignUpClick }) => {
  const [method, setMethod] = useState("mobileNumber");
  const [email, setEmail] = useState("");
  const [mobileNumber, setMobileNumber] = useState("");
  const [otpSent, setOtpSent] = useState(false);
  const [otp, setOtp] = useState("");
  const [error, setError] = useState("");

  const API_BASE = "http://localhost:9191/api/users";

  // === SEND OTP ===
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    const identifier = method === "email" ? email.trim() : mobileNumber.trim();

    if (!identifier) {
      setError(`Please enter your ${method === "email" ? "email" : "mobile number"}`);
      return;
    }

    // Basic validation
    if (method === "email") {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        setError("Invalid email format.");
        return;
      }
    } else {
      const mobileRegex = /^[0-9]{10}$/;
      if (!mobileRegex.test(mobileNumber)) {
        setError("Mobile number must be exactly 10 digits.");
        return;
      }
    }

    try {
      const response = await axios.post(`${API_BASE}/send-otp`, { identifier });
      alert(response.data);
      setOtpSent(true);
    } catch (error) {
      console.error("Error sending OTP:", error);
      setError(
        error.response?.data?.message || "Failed to send OTP. Try again."
      );
    }
  };

  // === VERIFY OTP ===
  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    setError("");

    const identifier = method === "email" ? email.trim() : mobileNumber.trim();

    if (!otp) {
      setError("Please enter OTP.");
      return;
    }

    try {
      const response = await axios.post(`${API_BASE}/verify-otp`, {
        identifier,
        otp,
      });

      alert(response.data);
    } catch (error) {
      console.error("Error verifying OTP:", error);
      setError(
        error.response?.data?.message || "OTP verification failed."
      );
    }
  };

  // === RESEND OTP ===
  const handleResendOtp = async () => {
    try {
      await axios.post(`${API_BASE}/send-otp`, {
        identifier: method === "email" ? email.trim() : mobileNumber.trim(),
      });
      alert("OTP resent successfully!");
    } catch (error) {
      console.error("Error resending OTP:", error);
      alert(error.response?.data?.message || "Failed to resend OTP.");
    }
  };

  // === GOOGLE LOGIN ===
  const handleGoogleLogin = async () => {
    try {
      const user = await signInWithGoogle();
      alert(`Welcome ${user.displayName || "User"}!`);
    } catch (error) {
      console.error(error);
      alert("Google login failed. Please try again.");
    }
  };

  return (
    <form
      onSubmit={otpSent ? handleVerifyOtp : handleSubmit}
      className="login-form"
    >
      {!otpSent ? (
        <>
          <div>
            <img
              src="https://tradebrains-wp.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/shiksha.png"
              alt="Shiksha Logo"
              className="logo"
            />
          </div>
          <h2>Log In to Shiksha</h2>

          <div className="method-toggle">
            <label>
              <input
                type="radio"
                name="method"
                value="email"
                checked={method === "email"}
                onChange={() => setMethod("email")}
              />
              Email
            </label>
            <label>
              <input
                type="radio"
                name="method"
                value="mobileNumber"
                checked={method === "mobileNumber"}
                onChange={() => setMethod("mobileNumber")}
              />
              Mobile Number
            </label>
          </div>

          {method === "email" ? (
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          ) : (
            <input
              type="tel"
              placeholder="Enter your mobile number"
              value={mobileNumber}
              onChange={(e) => setMobileNumber(e.target.value)}
            />
          )}

          {error && <div className="error">{error}</div>}

          <button type="submit">Get OTP</button>

          <div className="social-login">
            <p>Or</p>
            <button
              type="button"
              className="google-btn"
              onClick={handleGoogleLogin}
            >
              <img
                src="https://developers.google.com/identity/images/g-logo.png"
                alt="Google logo"
                className="google-logo"
              />
              Continue with Google
            </button>
          </div>

          <p className="switch-text">
            New to Shiksha?{" "}
            <button type="button" className="link-btn" onClick={onSignUpClick}>
              Sign Up
            </button>
          </p>
        </>
      ) : (
        <>
          <h2>Verify {method === "email" ? "Email" : "Mobile Number"}</h2>
          <p className="otp-info">
            One Time Password (OTP) has been sent to your{" "}
            {method === "email" ? "email address" : "mobile number"}.
          </p>

          <input
            type="text"
            placeholder="Enter OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />

          {error && <div className="error">{error}</div>}

          <div className="otp-actions">
            <button
              type="button"
              className="back-btn"
              onClick={() => {
                setOtpSent(false);
                setOtp("");
              }}
            >
              Back
            </button>
            <button type="submit" className="submit-btn">
              Verify OTP
            </button>
          </div>

          <p className="resend-text">
            Didn’t receive OTP?{" "}
            <button type="button" className="link-btn" onClick={handleResendOtp}>
              Resend OTP
            </button>
          </p>
        </>
      )}
    </form>
  );
};

export default LoginForm;
