import React from "react";
import { Link } from "react-router-dom";
import "./NavigationMenu.css"; // Import your custom CSS file

function NavigationMenu() {
  const customerId = localStorage.getItem("customerId");
  console.log("customerId:", customerId);
  return (
    <div className="navigation-menu">
      <Link to="/create-account">Create Account</Link>
      {/* <Link to="/add-beneficiary">Add Beneficiary</Link> */}
      {/* <Link to="/remove-beneficiary">Remove Beneficiary</Link> */}
      <Link to="/view-statement">View Statement</Link>
      <Link to="/view-profile">View Profile</Link>
      {/* <Link to={`/beneficiary-list`}>Beneficiary List</Link> */}
      <Link to={`/beneficiary-list/${customerId}`}>Beneficiary List</Link>
      {/* <Link to="/logout">Logout</Link> */}
      <Link to={`/money-transfer/${customerId}`}>Money Transfer</Link>
    </div>
  );
}

export default NavigationMenu;
