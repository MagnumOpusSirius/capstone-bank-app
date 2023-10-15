import React from "react";
import NavigationMenu from "./NavigationMenu";
import AccountList from "./AccountList";
import "./Dashboard.css";
import { useNavigate } from "react-router-dom";
function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    navigate("/");
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
        {/* Placeholder for other components */}
      </div>
    </div>
  );
}

export default Dashboard;
