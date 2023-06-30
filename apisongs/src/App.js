import './App.css';
import { Routes, Route } from 'react-router-dom';
import Songs from './pages/Songs';
import Login from './pages/Login';
import Home from './pages/Home';
import SignUp from './pages/SingUp';
import PlaylistDetails from './pages/PlaylistDetails';


function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/songs" element={<Songs />} /> 
        <Route path="/home" element={<Home />} /> 
        <Route path="/singUp" element={<SignUp />} /> 
        <Route path="/playlist/:playlistId" element={<PlaylistDetails />} />

      </Routes>
    </>
  );
}

export default App;
