import "./App.css";
import { Route, Routes } from "react-router-dom";
import LoginForm from "./Component/Login/login";
import Register from "./Component/Register/register";
import ForgetPassword from "./Component/Forget_Password/forget";
import Staff from "./Component/Staff_Login/staff";
import Dashboard from "./Component/Dashboard/Dashboard";
// import NavigationMenu from "./Component/Dashboard/NavigationMenu";
// import AccountList from "./Component/Dashboard/AccountList";
import CreateAccountForm from "./Component/account/CreateAccountForm";
import BeneficiaryList from "./Component/beneficiary/BeneficiaryList";
import ProfileUpdate from "./Component/profile/ProfileUpdate";
import MoneyTransfer from "./Component/transfer/MoneyTransfer";
import PasswordRecovery from "./Component/Forget_Password/PasswordRecovery";
import ResetPassword from "./Component/Forget_Password/ResetPassword";
function App() {
  const customerId = localStorage.getItem("customerId");
  // console.log("customerId in app.js:", customerId);
  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/staff-login" element={<Staff />} />
        <Route path="/register" element={<Register />} />
        {/* <Route path="/forgot-password" element={<ForgetPassword />} /> */}
        <Route path="/forgot-password" element={<PasswordRecovery />} />
        <Route path="/reset-password/:username" element={<ResetPassword />} />

        <Route path="/dashboard/:customerId" element={<Dashboard />} />
        {/* Nested routes for dashboard components */}
        {/* <Route path="/" element={<NavigationMenu />} /> */}
        {/* <Route path="account" element={<AccountList />} /> */}
        <Route path="/create-account" element={<CreateAccountForm />} />
        <Route
          path="/beneficiary-list/:customerId"
          element={<BeneficiaryList customerId={customerId} />}
        />
        <Route path="/view-profile" element={<ProfileUpdate />} />
        <Route path="/money-transfer/:customerId" element={<MoneyTransfer />} />
      </Routes>
    </div>
  );
}

export default App;
