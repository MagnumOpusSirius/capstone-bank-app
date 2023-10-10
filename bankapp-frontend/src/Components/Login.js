import React from "react";

const Login = props => {

    return (
        <div>
            <h4>Username</h4>
            <input type='text' placeholder="Enter Username" id="username"/>

            <h4>Password</h4>
            <input type='text' placeholder="Enter Password" id="password"/>

            <button>Login</button>
        </div>
    )
}




export default Login;