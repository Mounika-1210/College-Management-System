import React from "react";
import "./HomePage.css";

const HomePage = () => {
  return (
    <div
      className="home-container"
      style={{
        backgroundImage:
          "url('https://img.freepik.com/premium-photo/woman-is-using-laptop-outside-with-student-background_862335-18246.jpg?semt=ais_hybrid&w=740&q=80')", // ✅ background image address here
      }}
    >
      

      {/* Overlay for title + search bar */}
      <div className="overlay-content">
        <h1>Find Colleges, Courses, Exams & More</h1>
        <div className="search-box">
          <input
            type="text"
            placeholder="Search for colleges, courses, exams..."
          />
          <button>Search</button>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
