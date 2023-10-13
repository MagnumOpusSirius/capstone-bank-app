import React from "react";
import Dashboard_navbar from "./Dashboard_navbar";
import CreateAccount from "./CreateAccount";

function Dashboard() {
    return(
        <div>
        <h1>Dashboard view</h1>
        <Dashboard_navbar/>
        <CreateAccount/>
        </div>
    )
}
export default Dashboard