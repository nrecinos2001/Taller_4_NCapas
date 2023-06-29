import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Outlet, Link } from 'react-router-dom'

const SongList = () => {
    const [songs, setSongs] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                console.log(token);
                const response = await axios.get('http://localhost:8080/songs', {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setSongs(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, []);

    return (
        <>
            <div className="container mt-2 ml-10">
                <Link to="/dashboard"><button className="btn btn-info">REGRESAR</button></Link>
            </div>

            <div className="container mt-2">
                <div className="row justify-content-center">
                    <div className="col-6">
                        <table className="table table-dark text-center">
                            <thead >
                                <tr>
                                    <th>Título</th>
                                    <th>Duración</th>
                                </tr>
                            </thead>
                            <tbody>
                                {songs.map(song => (
                                    <tr key={song.code}>
                                        <td>{song.title}</td>
                                        <td>{song.duration}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <Outlet />
        </>


    );
};

export default SongList;
