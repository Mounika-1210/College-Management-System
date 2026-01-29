import React, { useState } from "react";
import LoginForm from "./components/LoginForm";
import SignUpForm from "./components/SignUpForm";
import Home from "./components/HomePage";
import Navbar from "./components/Navbar";
import "./components/AuthForm.css";  // ✅ Import CSS here


function App() {
  const [formType, setFormType] = useState(null); // null, 'login', or 'signup'

  return (
    <div className="app-container">
      <Navbar
        onLoginClick={() => setFormType("login")}
        onSignInClick={() => setFormType("signup")}
        onHomeClick={() => setFormType(null)}
      />

      {/* Home page stays in background */}
      <Home />

      {/* Popup forms */}
      {formType && (
        <div className="modal-overlay" onClick={() => setFormType(null)}>
          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()} // Prevent closing on inner click
          >
            {formType === "login" ? (
              <div className="auth-form-card">
                <LoginForm onSignUpClick={() => setFormType("signup")} />
              </div>
            ) : (
              <div className="auth-form-card">
                <SignUpForm onLoginClick={() => setFormType("login")} />
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default App;