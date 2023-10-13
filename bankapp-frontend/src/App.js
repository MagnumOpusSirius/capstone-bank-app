import logo from './logo.svg';
import './App.css';
import Home from './Components/Home';
import Navbar from './Components/Navbar';
import Dashboard from './Components/Dashboard';

function App() {
  return (
    <div className="App">
      <header className="App">
        <Navbar/>,
        <Home/>
        <Dashboard/>
      </header>
    </div>
  );
}

export default App;
