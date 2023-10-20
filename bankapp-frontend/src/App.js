import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from './Components/Home';
import Dashboard from './Components/Dashboard'
import Profile from './Components/Profile';
import AddBeneficiary from './Components/AddBeneficiary';
import RemoveBeneficiary from './Components/RemoveBeneficiary';
import TransferMoney from './Components/TransferMoney';
import ViewStatement from './Components/ViewStatement';

function App() {
  return (
    <div className="App">
      <header className="App">
        <Home/>
      </header>


      <Routes>
        <Route exact path="/" element={<Home />} />
        {/* <Route exact path="/add-beneficiary" element={<AddBeneficiary />} /> */}
        <Route exact path="/remove-beneficiary" element={<RemoveBeneficiary />} />
        <Route exact path="/transfer-money" element={<TransferMoney />} />
        <Route exact path="/view-statement" element={<ViewStatement />} />
        <Route exact path="/dahboard" element={<Dashboard />} />

      </Routes>
        
    </div>
  );
}

export default App;
