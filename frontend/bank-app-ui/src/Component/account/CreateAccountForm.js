import React, { useState } from "react";
import axios from "axios";

const CreateAccountForm = () => {
  const [initialDeposit, setInitialDeposit] = useState(0);
  const [accountType, setAccountType] = useState("SB");
  const [isCreatingAccount, setIsCreatingAccount] = useState(false);

  const handleInitialDepositChange = (event) => {
    setInitialDeposit(event.target.value);
  };

  //   const handleAccountTypeChange = (event) => {
  //     setAccountType(event.target.value);
  //   };

  //get the customer id from local storage
  const customerId = localStorage.getItem("customerId");
  const handleSubmit = async () => {
    //display a ccount creaation under process message
    setIsCreatingAccount(true);

    //set a POST request to create the account
    try {
      const response = await axios.post(
        `http://localhost:8086/api/customer/${customerId}/account/`,
        {
          accountType: accountType,
          accountBalance: initialDeposit,
          approved: "no",
        }
      );

      //check if the acount was created successfully
      if (response.status === 200) {
        alert("Account created successfully!");
      }
    } catch (error) {
      //handle error
      console.error("Error creating account:", error);
      alert("Account creation failed!");
    } finally {
      //reset the form
      setInitialDeposit(0);
      setAccountType("");
      setIsCreatingAccount(false);
    }
  };

  return (
    <div className="create-account-form">
      <h2>Create Account</h2>
      <label>
        Initial Deposit:
        <input
          type="number"
          value={initialDeposit}
          onChange={handleInitialDepositChange}
        />
      </label>
      <label>Account Type:</label>
      <select
        value={accountType}
        onChange={(e) => setAccountType(e.target.value)}
      >
        <option value="SB">Savings</option>
        <option value="CA">Checking</option>
        {/* Add other account types here */}
      </select>
      <button onClick={handleSubmit}>Create Account</button>
    </div>
  );
};

export default CreateAccountForm;
