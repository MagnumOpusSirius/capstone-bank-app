import React, { useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import "./ResetPassword.css"; // Import your CSS file

function ResetPassword() {
  const { username } = useParams();
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handlePasswordChange = async () => {
    try {
      const response = await axios.put(
        `http://localhost:8086/api/customer/${username}/forgot`,
        { username, password: newPassword }
      );

      if (response.status === 200) {
        setMessage("Password updated successfully");
        navigate("/");
      } else {
        setMessage("Sorry, password not updated");
      }
    } catch (error) {
      console.error("Error updating password:", error);
    }
  };

  return (
    <div className="reset-password-container">
      {" "}
      {/* Add a className */}
      <h2>Reset Password</h2>
      <p>Username: {username}</p>
      <label>New Password:</label>
      <input
        type="password"
        value={newPassword}
        onChange={(e) => setNewPassword(e.target.value)}
      />
      <button onClick={handlePasswordChange}>Change Password</button>
      <p className="message">{message}</p> {/* Add a className */}
    </div>
  );
}

export default ResetPassword;
