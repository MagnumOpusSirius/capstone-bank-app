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

  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent the default form submission behavior
    handlePasswordChange();
  };

  return (
    <div className="reset-password-container">
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
      </video>{" "}
      {/* Add a className */}
      <form onSubmit={handleSubmit}>
        <h2>Reset Password</h2>
        <p>Username: {username}</p>
        <label>New Password:</label>
        <input
          type="password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
        />
        <button type="submit">Change Password</button>
        <p className="message">{message}</p>
      </form>
    </div>
  );
}

export default ResetPassword;
