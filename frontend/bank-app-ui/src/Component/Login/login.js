// src/LoginForm.js
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './login.css'; 
import logo from '../logo.png'

const LoginForm = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your authentication logic here
    if (username && password) {
      onLogin(username);
    }
  };

  return (
    <div className="login-container">
      <div className = "top-corner">
        <Link to="/staff-login">Staff Corner</Link>
      </div>
      <div className = "top-logo">
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
          <Link to="/forgot-password" className='forget-password'>Forget Password?</Link>
      
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
