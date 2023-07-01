import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';

const Home = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [playlists, setPlaylists] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const token = localStorage.getItem('token');
  const navigate = useNavigate();
  const fetchPlaylists = async () => {
    try {
      let url = `http://localhost:8080/playlists/user?page=${currentPage}`;
      const response = await axios.get(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPlaylists(response.data?.content);
      setTotalPages(response.data?.totalPages);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchPlaylists();
  }, [currentPage]);

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
  useEffect(() => {
    fetchPlaylists();
  }, []);
  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      title: title,
      description: description,
    };

    axios
      .post('http://localhost:8080/playlists/', data, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        if (response.status === 201) {
          setTitle('');
          setDescription('');
          fetchPlaylists();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handlePlaylistClick = (playlistId) => {
    navigate(`/playlist/${playlistId}`);
  };

  return (
    <>
      <Navbar />
      <div className="hero text-center">
        <h1>¡Bienvenido!</h1>
        <p>Disfruta la música a tu manera</p>
      </div>

      <div className="d-flex justify-content-center align-items-center">
        <form className="mx-1 mx-md-4">
          <div className="row">
            <div className="col-sm-8">
              <input
                type="text"
                className="form-control form-control-lg"
                id="title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
                placeholder="Titulo"
              />
            </div>
            <div className="col-sm-8">
              <input
                type="text"
                className="form-control form-control-lg"
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
                placeholder="Descripción"
              />
            </div>
          </div>
          <div className="container mt-2 mb-4">
            <button type="submit" className="btn bg-black text-white btn-lg">
              Crear
            </button>
          </div>
        </form>
      </div>

      <div className="row justify-content-center">
        <div className="col-6">
          <table className="table table-bordered">
            <thead className="thead-dark">
              <tr>
                <th>Titulo de playlist</th>
                <th>Descripción</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {playlists.map((playlist) => (
                <tr key={playlist.code}>
                  <td>{playlist.title}</td>
                  <td>{playlist.description}</td>
                  <td>
                    <button
                      className="btn btn-primary"
                      onClick={() => handlePlaylistClick(playlist.code)}
                    >
                      Ver detalles
                    </button>
                  </td>
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
    </>
  );
};
//a
export default Home;
