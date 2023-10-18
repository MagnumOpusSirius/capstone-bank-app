import React, { useState } from "react";
import "./staff.css";
import { Link } from "react-router-dom";
import spaceBank from "../spaceBank.png";

const Staff = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    // Here you can implement logic to validate username and password
    // For simplicity, let's consider a basic validation
    if (username === "staff" && password === "password") {
      setIsLoggedIn(true);
    } else {
      alert("Invalid username or password. Please try again.");
    }
  };

  return (
    <div className="staff">
      <video
        autoPlay
        muted
        loop
        playsInline
        poster="/path-to-poster-image.jpg"
        className="background-video"
      >
        <source src="/videos/video-1.mp4" type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <div className="top-corner">
        <Link to="/">Home Page</Link>
      </div>
      <div className="top-logo">
        <img src={spaceBank} width={80} height={80} alt="Bank Logo" />
      </div>
      {isLoggedIn ? (
        <h2>Welcome, Staff Member!</h2>
      ) : (
        <div className="login-container2">
          <h2>Staff Login</h2>
          <div className="input-group2">
            <label>Username:</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="input-group2">
            <label>Password:</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button onClick={handleLogin}>Login</button>
          <div className="form-links">
            Own An Account With Us?
            <Link to="/"> Login Here </Link>
          </div>
        </div>
      )}
    </div>
  );
};

export default Staff;
