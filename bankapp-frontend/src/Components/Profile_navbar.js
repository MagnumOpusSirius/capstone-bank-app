import "./DashboardNavStyles.css";

import React from "react";
import { Link } from "react-router-dom";
const Profile_navbar = () => {
  return (
    <>
      <nav >
        <a href="index.html">
        </a>
        
        <div class="navbar">
          <ul id="nav-items">
            <li>
              <a href="create" to="/">
                Create Account
              </a>
            </li>
            
            <li>
              <a 
              href="add" to="add">
                Add Beneficiary
              </a>
            </li>
            
            <li>
              <a href="remove" to="remove">
                Remove Beneficiary
              </a>
            </li>

            <li>
              <a 
              href="transfer" to="transfer">
                Transfer Money
              </a>
            </li>

            <li>
              <a 
              href="view" to="view">
                View Statement
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};
export default Profile_navbar;