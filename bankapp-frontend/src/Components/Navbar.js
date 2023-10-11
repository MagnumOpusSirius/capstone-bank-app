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
              <a className="home" to="/">
                Home
              </a>
            </li>
            
            <li>
              <a 
              href="nav-link" to="dashboard">
                Dashboard
              </a>
            </li>
            
            <li>
              <a class="nav-link" to="Register">
                Register
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};

export default Navbar;