import React from "react";
import { Link } from "react-router-dom";
import "./TopBar.css";

function TopBar({ userName }) {
  return (
    <div className="top-bar">
      <div className="logo">Your Logo</div>
      <div className="welcome">Welcome, {userName}</div>
      <Link to="/logout" className="logout-button">
        Logout
      </Link>
    </div>
  );
}

export default TopBar;
