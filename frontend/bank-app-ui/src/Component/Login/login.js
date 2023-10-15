// src/LoginForm.js
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./login.css";
import logo from "../logo.png";
import axios from "axios";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // create a navigate function

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Add your authentication logic here
    // if (username && password) {
    //   onLogin(username);
    // }
    //========CHANGES========
    try {
      //send an authentication request to the server
      const response = await axios.post(
        "http://localhost:8086/api/customer/authenticate",
        { username, password }
      );

      //check if authentication is successful and a JWT token was received
      if (response.data && response.data.jwtToken) {
        //store the token in the local storage
        localStorage.setItem("jwtToken", response.data.jwtToken);
        //navigate to the dashboard
        navigate("/dashboard");
      } else {
        alert("Login Failed!");
      }
    } catch (error) {
      //handle error
      console.error("Authentication error:", error);
      alert("Login Failed!");
    }
  };

  return (
    <div className="login-container">
      <div className="top-corner">
        <Link to="/staff-login">Staff Corner</Link>
      </div>
      <div className="top-logo">
        <img src={logo} width={80} height={80} alt="Bank Logo" />
      </div>
      <div className="bank-logo">
        <img src={logo} width={250} height={250} alt="Bank Logo" />
      </div>
      <div className="login-form">
        <h2>Bank Login</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          {/* ... */}
          <button type="submit">Login</button>
          <Link to="/forgot-password" className="forget-password">
            Forget Password?
          </Link>

          <div className="form-links">
            Not A Customer?
            <Link to="/register">Register Here</Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
