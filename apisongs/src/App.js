import './App.css';
import { Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Songs from './pages/Songs';
import Login from './pages/Login';
import SingUP from './pages/SingUp';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/songs" element={<Songs />} />
        <Route path="/singup" element={<SingUP />} />
      </Routes>
    </>
  );
}

export default App;
