import React from "react";

const AccountTypeDropDown = () => {

    const [value, setValue] = React.useState('fruit');
   
    const handleChange = (event) => {
   
      setValue(event.target.value);
      setValue(event.target.value);
    };
   
    return (
      <div>
        <label>
          What kind of account?
   
          <select value={value} onChange={handleChange}>
            <option value="savings">Savings Account</option>
            <option value="current">Current Account</option>
          </select>
        </label>
        <p>Account type {value}!</p>
      </div>
    );
   
   };
   
   export default AccountTypeDropDown;