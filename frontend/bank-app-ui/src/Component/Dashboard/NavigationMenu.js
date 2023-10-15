import React from "react";
import { Link } from "react-router-dom";

function NavigationMenu() {
  return (
    <div className="navigation-menu">
      <Link to="/create-account">Create Account</Link>
      <Link to="/add-beneficiary">Add Beneficiary</Link>
      <Link to="/remove-beneficiary">Remove Beneficiary</Link>
      <Link to="/transfer-money">Transfer Money</Link>
      <Link to="/view-statement">View Statement</Link>
      <Link to="/view-profile">View Profile</Link>
      {/* <Link to="/logout">Logout</Link> */}
    </div>
  );
}

export default NavigationMenu;
