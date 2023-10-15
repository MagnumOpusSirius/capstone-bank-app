import "./App.css";
import { Route, Routes } from "react-router-dom";
import LoginForm from "./Component/Login/login";
import Register from "./Component/Register/register";
import ForgetPassword from "./Component/Forget_Password/forget";
import Staff from "./Component/Staff_Login/staff";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/staff-login" element={<Staff />} />
        <Route path="/register" element={<Register />} />
        <Route path="/forgot-password" element={<ForgetPassword />} />
      </Routes>
    </div>
  );
}

export default App;
