import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./PasswordRecovery.css";
function PasswordRecovery() {
  const [username, setUsername] = useState("");
  const [selectedQuestion, setSelectedQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (username && selectedQuestion && answer) {
      try {
        const response = await axios.get(
          `http://localhost:8086/api/customer/${username}/forgot/${selectedQuestion}/${answer}`
        );

        if (response.status === 200) {
          // If the details are validated, you can redirect to the password reset page
          navigate(`/reset-password/${username}`);
        } else {
          setError("Invalid details. Please try again.");
        }
      } catch (error) {
        console.error("Error validating details:", error);
        setError("An error occurred. Please try again.");
      }
    } else {
      setError("Please fill in all required fields.");
    }
  };

  return (
    <div>
      <h2>Password Recovery</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label>Select Security Question:</label>
          <select
            value={selectedQuestion}
            onChange={(e) => setSelectedQuestion(e.target.value)}
          >
            <option value="">Select a security question</option>
            <option value="question1">What is your favorite color?</option>
            <option value="question2">What is your pet's name?</option>
          </select>
        </div>
        <div>
          <label>Answer:</label>
          <input
            type="text"
            value={answer}
            onChange={(e) => setAnswer(e.target.value)}
          />
        </div>
        <button type="submit">Validate</button>
      </form>
      {error && <p className="error-message">{error}</p>}
    </div>
  );
}

export default PasswordRecovery;
