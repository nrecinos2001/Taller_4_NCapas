import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Navbar() {
    const navigate = useNavigate();

    const handleOut = () => {
        localStorage.removeItem('token');
        toast.error('Sesión terminada', {
            autoClose: 1000,
            closeButton: false,
        });
        navigate('/');
    };

    return (
        <>
            <ToastContainer position="top-right" />
            <nav className="navbar navbar-expand-sm navbar-light bg-light">
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item active">
                            <p className="nav-link">Inicio</p>
                        </li>
                        <li className="nav-item">
                            <p className="nav-link">Playlist</p>
                        </li>
                        <Link to="/songs">
                            <li className="nav-item">
                                <p className="nav-link">Songs</p>
                            </li>
                        </Link>
                        <li className="nav-item">
                            <p className="nav-link">Otros</p>
                        </li>
                    </ul>
                </div>
                <button onClick={handleOut} className="btn btn-danger pr-20" type="button">
                    SALIR
                </button>
            </nav>
        </>
    );
}

export default Navbar;
