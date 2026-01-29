import React, { useState } from 'react';
import './Navbar.css';

const Navbar = ({ onSignInClick, onLoginClick, onSearchClick }) => {
  const [searchText, setSearchText] = useState('');

  const handleSearch = () => {
    if (searchText.trim() !== '') {
      onSearchClick(searchText);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') handleSearch();
  };

  return (
    <nav className="navbar">
      {/* Left: Logo */}
      <div className="navbar-left">
        <img
          src="https://tradebrains-wp.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/shiksha.png"
          alt="Shiksha Logo"
          className="logo"
        />
      </div>

      {/* Center: Search bar */}
      <div className="navbar-center">
        <div className="search-container">
          <input
            type="text"
            className="search-input"
            placeholder="Search colleges, courses, exams, articles"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
            onKeyDown={handleKeyDown}
          />
          <button className="btn search-btn" onClick={handleSearch}>
            Search
          </button>
        </div>
      </div>

      {/* Right: Buttons */}
      <div className="navbar-right">
        <button className="btn" onClick={onSignInClick}>Sign Up</button>
        <button className="btn btn-primary" onClick={onLoginClick}>Login</button>
      </div>
    </nav>
  );
};

export default Navbar;