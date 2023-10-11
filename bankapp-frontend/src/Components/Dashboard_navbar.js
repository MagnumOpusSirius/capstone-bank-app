import React from "react";
import { Link } from "react-router-dom";

const Dashboard_navbar = () => {
  return (
    <>
      <nav class="dashboard_navbar dashboard_navbar-expand-md navbar-dark bg-dark fixed-top">
        
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <Link class="nav-link" to="/">
                Profile
              </Link>
            </li>
            
            <li class="nav-item">
              <Link class="nav-link" to="logout">
                Logout
              </Link>
            </li>
            
            <li class="nav-item">
              <Link class="nav-link" to="Register">
                Welcome, {User}
              </Link>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};

export default Dashboard_navbar;