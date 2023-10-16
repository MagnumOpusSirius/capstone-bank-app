import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from './Components/Home';
import Dashboard from './Components/Dashboard'
import Profile from './Components/Profile';
import AddBeneficiary from './Components/AddBeneficiary';

function App() {
  return (
    <div className="App">
      <header className="App">
        <Dashboard/>
      </header>


      <Routes>
        <Route exact path="/" element={<Home />} />
        {/* <Route exact path="/add-beneficiary" element={<AddBeneficiary />} /> */}
        <Route exact path="/remove-beneficiary" element={<Home />} />
        <Route exact path="/transfer" element={<Home />} />


      </Routes>
        
    </div>
  );
}

export default App;
