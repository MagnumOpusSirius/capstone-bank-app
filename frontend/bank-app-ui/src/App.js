import "./App.css";
import { Route, Routes } from "react-router-dom";
import LoginForm from "./Component/Login/login";
import Register from "./Component/Register/register";
import ForgetPassword from "./Component/Forget_Password/forget";
import Staff from "./Component/Staff_Login/staff";
import Dashboard from "./Component/Dashboard/Dashboard";
import NavigationMenu from "./Component/Dashboard/NavigationMenu";
import AccountList from "./Component/Dashboard/AccountList";
function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/staff-login" element={<Staff />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgot-password" element={<ForgetPassword />} />
        <Route path="/dashboard" element={<Dashboard />} />
        {/* Nested routes for dashboard components */}
        <Route path="/" element={<NavigationMenu />} />
        <Route path="/" element={<AccountList />} />
      </Routes>
    </div>
  );
}

export default App;
