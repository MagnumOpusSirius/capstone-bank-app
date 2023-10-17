import React, { useState, useEffect } from "react";
import axios from "axios";
import "./MoneyTransfer.css";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
function MoneyTransfer() {
  const { customerId } = useParams();
  const [accounts, setAccounts] = useState([]);
  const [sourceAccountNumber, setSourceAccountNumber] = useState("");
  const [amount, setAmount] = useState("");
  const [reason, setReason] = useState("");
  const [message, setMessage] = useState("");
  const [toAccountNumber, setToAccountNumber] = useState(""); // New state variable

  useEffect(() => {
    // Fetch the customer's accounts when the component mounts
    axios
      .get(`http://localhost:8086/api/customer/${customerId}/account/`)
      .then((response) => {
        setAccounts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching accounts:", error);
      });
  }, [customerId]);

  const handleTransfer = () => {
    if (sourceAccountNumber && toAccountNumber && amount && reason) {
      const transferData = {
        fromAccountNumber: parseInt(sourceAccountNumber),
        toAccountNumber: parseInt(toAccountNumber), // Parse to an integer
        amount: parseInt(amount),
        reason: reason,
        by: "customer",
      };

      // Send a request to transfer money
      axios
        .put(`http://localhost:8086/api/customer/transfer`, transferData)
        .then((response) => {
          if (response.status === 200) {
            setMessage("Transaction successful!");
          } else {
            setMessage("Failed to complete the transaction.");
          }
        })
        .catch((error) => {
          console.error("Error transferring money:", error);
          setMessage("Failed to complete the transaction.");
        });
    } else {
      setMessage("Please fill in all required fields.");
    }
  };

  return (
    <div className="money-transfer-container">
      <Link to={`/dashboard/${customerId}`} className="back-button">
        Back
      </Link>
      <h2>Money Transfer</h2>

      <div className="account-selection">
        <label>Source Account:</label>
        <select
          value={sourceAccountNumber}
          onChange={(e) => setSourceAccountNumber(e.target.value)}
        >
          <option value="">Select an account</option>
          {accounts.map((account) => (
            <option key={account.accountNumber} value={account.accountNumber}>
              {account.accountNumber}
            </option>
          ))}
        </select>
      </div>
      <div className="account-selection">
        <label>To Account:</label>
        <input
          type="text"
          value={toAccountNumber}
          onChange={(e) => setToAccountNumber(e.target.value)}
          placeholder="Enter To Account Number"
        />
      </div>
      <div className="amount-input">
        <label>Amount:</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Enter Amount"
        />
      </div>
      <div className="reason-input">
        <label>Reason for Transfer:</label>
        <input
          type="text"
          value={reason}
          onChange={(e) => setReason(e.target.value)}
          placeholder="Enter Reason"
        />
      </div>
      <button className="transfer-button" onClick={handleTransfer}>
        Transfer Money
      </button>
      {message && <p className="success-message">{message}</p>}
    </div>
  );
}

export default MoneyTransfer;
