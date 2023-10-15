import React, { useState } from "react";
import axios from "axios";
import "./Register.css";
import logo from "../logo.png";
import { Link, useNavigate } from "react-router-dom";

const Register = () => {
  const [formData, setFormData] = useState({
    username: "",
    fullName: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  //========CHANGES========
  const [error, setError] = useState("");
  const navigate = useNavigate(); // create a navigate function

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Perform registration logic here with formData
    // console.log(formData);
    //========CHANGES========
    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8086/api/customer/register",
        formData
      );

      //handle success, redirect to login page
      if (response.status === 200) {
        alert("Registration Successful!");
        navigate("/");
      } else {
        // Handle the case where the registration failed
        if (response.status === 400 && response.data) {
          alert(response.data);
        } else {
          alert("Registration Failed!");
        }
      }
    } catch (error) {
      //handle error
      console.log(error);
    }
  };

  return (
    <div className="register-container">
      <div className="top-corner">
        <Link to="/staff-login">Staff Corner</Link>
      </div>
      <div className="top-logo">
        <img src={logo} width={80} height={80} alt="Bank Logo" />
      </div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Username</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Full Name</label>
          <input
            type="text"
            name="fullName"
            value={formData.fullName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Confirm Password</label>
          <input
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Register</button>
        <div className="form-links">
          Already have an account?
          <Link to="/">Login Here</Link>
        </div>
      </form>
      {/* ========CHANGES======== */}
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default Register;
