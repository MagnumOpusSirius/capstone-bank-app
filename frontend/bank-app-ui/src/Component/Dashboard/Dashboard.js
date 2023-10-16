import React, { useState } from "react";
import NavigationMenu from "./NavigationMenu";
import AccountList from "./AccountList";
import "./Dashboard.css";
import { useNavigate } from "react-router-dom";
import CreateAccountForm from "../account/CreateAccountForm";

function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("customerId");
    navigate("/");
  };

  const [showCreateAccountForm, setShowCreateAccountForm] = useState(false);
  const handleCreateAccountClick = () => {
    setShowCreateAccountForm(true);
  };

  return (
    <div className="dashboard">
      <div className="navigation-menu">
        <NavigationMenu />
      </div>
      <div className="dashboard-content">
        <button className="logout" onClick={handleLogout}>
          Logout
        </button>
        <AccountList />
        {showCreateAccountForm ? <CreateAccountForm /> : null}
        <button
          className="create-account-button"
          onClick={handleCreateAccountClick}
        >
          Create Account
        </button>
        {/* Placeholder for other components */}
      </div>
    </div>
  );
}

export default Dashboard;
