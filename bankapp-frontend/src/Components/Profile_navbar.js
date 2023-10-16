import "./Profile_navbarStyles.css";

import React from "react";
import { Link } from "react-router-dom";
const Profile_navbar = () => {
  return (
        <div class="profile-navbar">
            <Link to="/create-account">Create Account</Link>
            <Link to="/add-beneficiary">Add Beneficiary</Link>
            <Link to="/remove-beneficiary">Remove Beneficiary</Link>
            <Link to="/transfer-money">Transfer Money</Link>
            <Link to="/view-statement">View Statement</Link>
        </div>
  );
};
export default Profile_navbar;