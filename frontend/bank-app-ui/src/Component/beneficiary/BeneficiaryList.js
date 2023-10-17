import React, { useState, useEffect } from "react";
import axios from "axios";
import "./BeneficiaryList.css";
const customerId = localStorage.getItem("customerId");
console.log("first customerId in list:", customerId);
function BeneficiaryList({ customerId }) {
  const [beneficiaries, setBeneficiaries] = useState([]);
  console.log("second customerId in list:", customerId);
  useEffect(() => {
    //fetch the list of beneficiaries when the component mounts
    axios
      .get(`http://localhost:8086/api/customer/${customerId}/beneficiary`)
      .then((response) => {
        console.log("Response Data:", response.data);
        setBeneficiaries(response.data);
      })
      .catch((error) => {
        console.error("Error fetching beneficiaries:", error);
      });
  }, [customerId]);

  const handleRemoveBeneficiary = (beneficiaryId) => {
    //send a request to remove the beneficiary
    axios
      .delete(
        `http://localhost:8086/api/customer/${customerId}/beneficiary/${beneficiaryId}`
      )
      .then(() => {
        // Remove the beneficiary from the local state
        setBeneficiaries((prevBeneficiaries) =>
          prevBeneficiaries.filter((b) => b.beneficiaryId !== beneficiaryId)
        );
      })
      .catch((error) => {
        console.error("Error removing beneficiary:", error);
      });
  };

  return (
    <div className="beneficiary-list">
      <h3>Beneficiaries</h3>
      <ul>
        {beneficiaries.map((beneficiary) => (
          <li key={beneficiary.beneficiaryId} className="beneficiary-item">
            <div className="beneficiary-details">
              <p>Account Number: {beneficiary.beneficiaryAccountNumber}</p>
              <p>Name: {beneficiary.beneficiaryName || "N/A"}</p>
              <p>Active: {beneficiary.active === "yes" ? "Yes" : "No"}</p>
            </div>
            <button
              onClick={() => handleRemoveBeneficiary(beneficiary.beneficiaryId)}
              className="remove-button"
            >
              Remove
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BeneficiaryList;
