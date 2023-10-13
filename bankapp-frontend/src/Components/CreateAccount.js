import React from "react";
import Boxes from "./Boxes";

const CreateAccount = props => {

    return (
        <div>
            <h4>CreateAccountForm</h4>
            <input type='text' placeholder="Enter Initial Deposit" id="username"/>

            <h4>Select type of account</h4>
            <Boxes/>
            <button>Submit</button>
        </div>
    )
}

export default CreateAccount;