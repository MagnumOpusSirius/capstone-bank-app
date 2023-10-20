import React from "react";
import { Link } from "react-router-dom";
import "./Login.css";


const Login = props => {

    return (
        <div className="login-container">
        <video
          autoPlay
          muted
          loop
          playsInline
          poster="/path-to-poster-image.jpg"
          className="background-video"
        >
          <source src="/videos/video-4.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
        <div className="top-corner">
          <Link to="/staff-login">Staff Corner</Link>
          <Link to="/dashboard">Dashboard</Link>


        </div>
        
        <div className="login-form">
          <h2>Bank Login</h2>
          <form>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
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
    )
}




export default Login;