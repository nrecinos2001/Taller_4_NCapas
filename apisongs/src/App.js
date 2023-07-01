import { Routes, Route, Navigate } from 'react-router-dom';
import Songs from './pages/Songs';
import Login from './pages/Login';
import SingUp from './pages/SingUp';
import Home from './pages/Home';
import PlaylistDetails from './pages/PlaylistDetails';

function ProtectedRoute({ children }) {
  const token = localStorage.getItem('token');

  if (!token) {
    return <Navigate to="/" replace />;
  }

  return children;
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route
        path="/home*"
        element={<ProtectedRoute><Home /></ProtectedRoute>}
      />
      <Route
        path="/songs*"
        element={<ProtectedRoute><Songs /></ProtectedRoute>}
      />
      <Route path="/playlist/:playlistId" element={<ProtectedRoute><PlaylistDetails /></ProtectedRoute>} />

      <Route path="/singup" element={<SingUp />} />
    </Routes>
  );
}

export default App;



