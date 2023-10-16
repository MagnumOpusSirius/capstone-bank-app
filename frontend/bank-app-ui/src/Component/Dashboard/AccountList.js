import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

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
    <div className="account-list">
      <h3>Your Accounts:</h3>
      <ul>
        {accounts.map((account) => (
          <li key={account.accountNumber}>
            <p>Account Number: {account.accountNumber}</p>
            <p>Account Type: {account.accountType}</p>
            <p>Account Balance: ${account.accountBalance.toFixed(2)}</p>
            <p>Account Status: {account.accountStatus}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AccountList;
