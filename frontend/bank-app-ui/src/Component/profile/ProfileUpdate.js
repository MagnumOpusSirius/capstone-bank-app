import axios from "axios";
import React, { useState } from "react";
import "./ProfileUpdate.css";
function ProfileUpdate() {
  const [profileData, setProfileData] = useState({
    customerId: localStorage.getItem("customerId"), //set the customer id from local storage, which is not editable
    fullName: "",
    phone: "",
    pan: "",
    aadhar: "",
    secretQuestion: "question1",
    secretAnswer: "",
  });

  // Use localStorage to retrieve and set the customer ID
  //   const customerId = localStorage.getItem("customerId");
  //   setProfileData({ ...profileData, customerId });

  //define a function to handle the input change:
  function handleInputChange(event) {
    const { name, value } = event.target;
    setProfileData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  }
  //define a function to handle the form submission:
  function handleFormSubmit(event) {
    event.preventDefault(); //prevent default form submission behavior
    //perform the form submission logic here
    //send a request to update the profile data:
    axios
      .put(
        `http://localhost:8086/api/customer/${profileData.customerId}`,
        profileData
      )
      .then((response) => {
        if (response.status === 200) {
          alert("Profile updated successfully!");
        } else {
          alert("Failed to update profile.");
        }
      })
      .catch((error) => {
        console.error("Error updating profile:", error);
        alert("Failed to update profile.");
      });
  }
  return (
    <div className="profile-update">
      <div className="profile-form">
        <h2>Profile Update</h2>
        <form onSubmit={handleFormSubmit}>
          <div>
            <label>Customer ID:</label>
            <input
              type="text"
              className="readonly"
              value={profileData.customerId}
              readOnly
            />
          </div>
          <div>
            <label>Full Name:</label>
            <input
              type="text"
              name="fullName"
              value={profileData.fullName}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Phone:</label>
            <input
              type="text"
              name="phone"
              value={profileData.phone}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>PAN:</label>
            <input
              type="text"
              name="pan"
              value={profileData.pan}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Aadhar:</label>
            <input
              type="text"
              name="aadhar"
              value={profileData.aadhar}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label>Security Question:</label>
            <select
              name="secretQuestion"
              value={profileData.secretQuestion}
              onChange={handleInputChange}
            >
              <option value="question1">What is your favorite color?</option>
              <option value="question2">What is your pet's name?</option>
              {/* Add more security questions here */}
            </select>
          </div>
          <div>
            <label>Security Answer:</label>
            <input
              type="text"
              name="secretAnswer"
              value={profileData.secretAnswer}
              onChange={handleInputChange}
            />
          </div>
          <button type="submit">Update Profile</button>
        </form>
      </div>
    </div>
  );
}

export default ProfileUpdate;
