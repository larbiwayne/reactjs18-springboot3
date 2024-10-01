import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom';  // Import Routes and Route from react-router-dom
import LoginPage from './pages/LoginPage';  // Import the LoginPage component
import AdminPage from './pages/AdminPage';  // Import the AdminPage component
import UserPage from './pages/UserPage';  // Import the UserPage component
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/admin" element={<AdminPage />} />
        <Route path="/user" element={<UserPage />} />
      </Routes>
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
