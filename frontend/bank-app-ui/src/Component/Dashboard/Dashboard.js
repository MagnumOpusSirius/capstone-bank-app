import React, { useState, useEffect } from "react";
import NavigationMenu from "./NavigationMenu";
import AccountList from "./AccountList";
import "./Dashboard.css";
import { useNavigate } from "react-router-dom";
import CreateAccountForm from "../account/CreateAccountForm";
import AddBeneficiaryForm from "../beneficiary/AddBeneficiaryForm";
// import TopBar from "./TopBar";
import axios from "axios";

function Dashboard() {
  const navigate = useNavigate();
  const customerId = localStorage.getItem("customerId");
  console.log("customerId in dashboard:", customerId);
  const [userName, setUserName] = useState("");
  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("customerId");
    navigate("/");
  };

  const [showCreateAccountForm, setShowCreateAccountForm] = useState(false);
  const handleCreateAccountClick = () => {
    setShowCreateAccountForm(true);
  };

  const [showAddBeneficiaryForm, setShowAddBeneficiaryForm] = useState(false);
  const handleAddBeneficiary = (beneficiaryRequest) => {
    // const customerId = localStorage.getItem("customerId");
    axios
      .post(
        `http://localhost:8086/api/customer/${customerId}/beneficiary`,
        beneficiaryRequest
      )
      .then((response) => {
        if (response.status === 200) {
          alert("Beneficiary Added Successfully!");
          setShowAddBeneficiaryForm(false);
        } else {
          alert("Failed to add beneficiary!");
        }
      })
      .catch((error) => {
        console.error("Error adding beneficiary:", error);
        alert("Failed to add beneficiary!");
      });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8086/api/customer/${customerId}`)
      .then((response) => {
        setUserName(response.data.username);
      })
      .catch((error) => {
        console.error("Error fetching user name:", error);
      });
  }, []);

  return (
    <div className="dashboard">
      {/* <TopBar userName={userName} /> */}
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
        <button onClick={() => setShowAddBeneficiaryForm(true)}>
          Add Beneficiary
        </button>

        {showAddBeneficiaryForm && (
          <AddBeneficiaryForm onAddBeneficiary={handleAddBeneficiary} />
        )}
        {/* Placeholder for other components */}
        {/* Render the BeneficiaryList component with the customerId prop */}
        {/* <BeneficiaryList customerId={customerId} /> */}
      </div>
    </div>
  );
}

export default Dashboard;
