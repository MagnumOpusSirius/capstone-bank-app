import logo from './logo.svg';
import './App.css';
import Home from './Components/Home';
import Navbar from './Components/Navbar';

function App() {
  return (
    <div className="App">
      <header className="App">
        <Navbar/>,
        <Home/>
      </header>
    </div>
  );
}

export default App;
