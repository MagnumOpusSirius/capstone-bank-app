import React from "react";
import Boxes from "./Boxes";

const AddBeneficiary = props => {

    return (
        <div>
            <h4>AddBeneficiary</h4>
            <input type='text' placeholder="Enter Account Number" id="AccNumber"/>
            <button>Confirm Account</button>


            <h4>Select type of account</h4>
            <Boxes/>
            <button>Submit</button>
        </div>
    )
}

export default AddBeneficiary;