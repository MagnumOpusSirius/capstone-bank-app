import React from "react";
import Dashboard_navbar from "./Dashboard_navbar";
import Profile_navbar from "./Profile_navbar";
import Profile from "./Profile";

function Dashboard() {
    return(
        <div>
        <h1>Dashboard view</h1>
        <Dashboard_navbar/>
            <div className="profile-navbar">
            <Profile_navbar/>
            </div>
            <div className="profile-components">
                <Profile/>
            </div>
        </div>

        
    )
}
export default Dashboard