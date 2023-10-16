import React, { useState } from "react";

const AddBeneficiaryForm = ({ onAddBeneficiary }) => {
  const [accountNumber, setAccountNumber] = useState("");
  const [accountType, setAccountType] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    //create a request object
    const beneficiaryRequest = {
      accountNumber: parseInt(accountNumber),
      accountType,
      approved: "no",
    };
    onAddBeneficiary(beneficiaryRequest);
  };
  return (
    <div>
      <h2>Add Beneficiary</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Account Number:</label>
          <input
            type="number"
            value={accountNumber}
            onChange={(e) => setAccountNumber(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Type of Account (CA or SB):</label>
          <input
            type="text"
            value={accountType}
            onChange={(e) => setAccountType(e.target.value)}
            required
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default AddBeneficiaryForm;
