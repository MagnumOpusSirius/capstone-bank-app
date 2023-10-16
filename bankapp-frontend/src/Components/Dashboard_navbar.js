import "./DashboardNavStyles.css";

import React from "react";
import { Link } from "react-router-dom";
const Dashboard_navbar = () => {
  return (
    <>
      <nav >
        <a href="index.html">
        </a>
        
        <div class="navbar">
          <ul id="nav-items">
            <li>
              <a className="profile" to="/">
                Profile
              </a>
            </li>
            
            <li>
              <a 
              href="nav-link" to="logout">
                Logout
              </a>
            </li>
            
            <li>
              <a class="nav-link" to="Welcome">
                Welcome
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};
export default Dashboard_navbar;