import React from 'react'
import { Outlet, Link } from 'react-router-dom'




function Navbar() {
    
        const handleOut = () => {
          localStorage.removeItem('token');
          alert('Sesion Culminada');
        };

    return (
        <>
            <nav className="navbar navbar-expand-sm navbar-light bg-light">
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Inicio</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Playlist</a>
                        </li>
                        <Link to="/songs"><li className="nav-item">
                            <a className="nav-link" href="#">Songs</a>
                        </li></Link>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Otros</a>
                        </li>
                    </ul>
                </div>
                <Link to="/"><button onClick={handleOut} className="btn btn-danger pr-20" type="button">SALIR</button></Link>
            </nav>
            <Outlet />
        </>
    )
}

export default Navbar