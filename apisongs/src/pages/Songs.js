import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Outlet, Link } from 'react-router-dom';

const Songs = () => {
    const [songs, setSongs] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [lastPage, setLastPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [searchTerm, setSearchTerm] = useState('');
    const [filteredSongs, setFilteredSongs] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                let url = `http://localhost:8080/songs?fragment=${searchTerm}&page=${currentPage}`;
                const response = await axios.get(url, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setSongs(response.data?.content);
                setTotalPages(response.data?.totalPages);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, [searchTerm, currentPage]);

    const goToNextPage = () => {
        if (currentPage < totalPages - 1) {
            setCurrentPage(currentPage + 1);
        }
    };

    const goToPreviousPage = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const isLastPage = currentPage === totalPages - 1;

    const handleSearch = (event) => {
        const value = event.target.value;
        setSearchTerm(value);
        setCurrentPage(0); // Reiniciar currentPage solo si se ingresa un valor de búsqueda

        if (value === '') {
            setCurrentPage(lastPage); // Restaurar la última página visitada al vaciar el campo de búsqueda
        }
    };

    useEffect(() => {
        if (searchTerm === '') {
            setLastPage(currentPage); // Actualizar lastPage solo cuando no hay un término de búsqueda
        }

        const filteredContent = songs.filter((song) =>
            song.title.toLowerCase().includes(searchTerm.toLowerCase())
        );
        setFilteredSongs(filteredContent);
    }, [songs, searchTerm, currentPage]);

    return (
        <>
            <div className="container mt-2 ml-10">
                <Link to="/home">
                    <button className="btn btn-info">REGRESAR</button>
                </Link>
            </div>

            <div className="container mt-2">
                <div className="row justify-content-center">
                    <div className="col-6">
                        <input
                            type="text"
                            className="form-control mb-2"
                            placeholder="Buscar por título"
                            value={searchTerm}
                            onChange={handleSearch}
                        />
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-6">
                        <table className="table table-dark text-center">
                            <thead>
                                <tr>
                                    <th>Título</th>
                                    <th>Duración</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filteredSongs.map((song) => (
                                    <tr key={song.code}>
                                        <td>{song.title}</td>
                                        <td>{song.duration}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-6">
                        <button
                            className="btn btn-primary"
                            disabled={currentPage === 0}
                            onClick={goToPreviousPage}
                        >
                            Anterior
                        </button>
                        <button
                            className="btn btn-primary ml-2"
                            disabled={isLastPage}
                            onClick={goToNextPage}
                        >
                            Siguiente
                        </button>
                    </div>
                </div>
            </div>
            <Outlet />
        </>
    );
};

export default Songs;
