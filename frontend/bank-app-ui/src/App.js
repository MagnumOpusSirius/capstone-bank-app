import "./App.css";
import { Route, Routes } from "react-router-dom";
import LoginForm from "./Component/Login/login";
import Register from "./Component/Register/register";
import ForgetPassword from "./Component/Forget_Password/forget";
import Staff from "./Component/Staff_Login/staff";
import Dashboard from "./Component/Dashboard/Dashboard";
import NavigationMenu from "./Component/Dashboard/NavigationMenu";
import AccountList from "./Component/Dashboard/AccountList";
import CreateAccountForm from "./Component/account/CreateAccountForm";
function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/staff-login" element={<Staff />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgot-password" element={<ForgetPassword />} />
        <Route path="/dashboard/:customerId" element={<Dashboard />} />
        {/* Nested routes for dashboard components */}
        {/* <Route path="/" element={<NavigationMenu />} /> */}
        {/* <Route path="account" element={<AccountList />} /> */}
        <Route path="/create-account" element={<CreateAccountForm />} />
      </Routes>
    </div>
  );
}

export default App;
