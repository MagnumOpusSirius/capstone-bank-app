import React, { useState } from "react";
import "./forget.css";
import logo from "../logo.png"
import { Link } from "react-router-dom";

const ForgetPassword = () => {
  const [formData, setFormData] = useState({
    username: "",
    securityQuestion: "",
    answer: "",
  });

  const securityQuestions = ["What is your pet's name?", "Where were you born?", "What is your favorite color?"];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Perform logic to get details based on the entered data
    console.log(formData);
    // Reset the form after submission
    setFormData({
      username: "",
      securityQuestion: "",
      answer: "",
    });
  };

  return (
    <div className="forget-password-container">
      <div className = "top-corner">
        <Link to="/staff-login">Staff Corner</Link>
      </div>
      <div className = "top-logo">
        <img src={logo} width={80} height={80} alt="Bank Logo" />
      </div>
      <h2>Forget Password</h2>
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
          <label>Security Question</label>
          <select
            name="securityQuestion"
            value={formData.securityQuestion}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Select a security question</option>
            {securityQuestions.map((question, index) => (
              <option key={index} value={question}>
                {question}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Answer</label>
          <input
            type="text"
            name="answer"
            value={formData.answer}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Get Details</button>
        <div className="form-links">
            Already have an account?
            <Link to="/">Login Here</Link>
        </div>
      </form>
    </div>
  );
};

export default ForgetPassword;
