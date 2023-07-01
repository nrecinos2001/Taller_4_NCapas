import { Routes, Route, Navigate } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Songs from './pages/Songs';
import Login from './pages/Login';
import SingUp from './pages/SingUp';

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
        path="/dashboard/*"
        element={<ProtectedRoute><Dashboard /></ProtectedRoute>}
      />
      <Route
        path="/songs/*"
        element={<ProtectedRoute><Songs /></ProtectedRoute>}
      />
      <Route path="/singup" element={<SingUp />} />
    </Routes>
  );
}

export default App;



