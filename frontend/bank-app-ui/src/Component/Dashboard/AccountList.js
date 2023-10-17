import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./AccountList.css";
function AccountList() {
  const [accounts, setAccounts] = useState([]);
  const { customerId } = useParams();

  useEffect(() => {
    const fetchAccounts = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8086/api/customer/${customerId}/account/`
        );
        setAccounts(response.data);
      } catch (error) {
        console.error("Error fetching accounts:", error);
      }
    };

    fetchAccounts();
  }, [customerId]);

  return (
    <div className="container mt-5">
      <h3>Your Accounts:</h3>
      <table className="table">
        <thead>
          <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Account Balance</th>
            <th>Account Status</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((account) => (
            <tr key={account.accountNumber}>
              <td>{account.accountNumber}</td>
              <td>{account.accountType}</td>
              <td>${account.accountBalance.toFixed(2)}</td>
              <td
                style={{
                  color: account.accountStatus === "DISABLE" ? "red" : "green",
                }}
              >
                {account.accountStatus}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AccountList;
