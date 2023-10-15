import React from "react";

function AccountList() {
  //fetch and display the users accounts list
  const userAccounts = [];

  return (
    <div className="account-list">
      <h3>Accounts</h3>
      <ul>
        {userAccounts.map((account) => (
          <li key={account.id}>{account.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default AccountList;
