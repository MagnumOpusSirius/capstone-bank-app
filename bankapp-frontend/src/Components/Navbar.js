import "./NavbarStyles.css";

import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <>
      <nav >
        <a href="index.html">

        </a>
        
        <div class="navbar">
          <ul id="nav-items">
            <li>
              <a href="home" className="home" to="/">
                Home
              </a>
            </li>
            
            <li>
              <a 
              href="{{Profile.js}}" to="dashboard">
                Dashboard
              </a>
            </li>
            
            <li>
              <a href="staff" to="Register">
                Staff Corner
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};

export default Navbar;