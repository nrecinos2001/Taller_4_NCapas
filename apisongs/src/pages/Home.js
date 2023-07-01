import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Home = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [playlists, setPlaylists] = useState([]);

  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const [searchTerm, setSearchTerm] = useState('');
  const [filteredPlaylist, setFilteredPlaylist] = useState([]);
  const [lastPage, setLastPage] = useState(0);


  const token = localStorage.getItem('token');

  const clearLabels = () => {
    setTitle('');
    setDescription('');
  }

  const navigate = useNavigate();
  const fetchPlaylists = async () => {
    try {
      let url = `http://localhost:8080/playlists/user?fragment=${searchTerm}&page=${currentPage}`;
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
  useEffect(() => {
    fetchPlaylists();
  }, []);

  const handleSearch = (event) => {
    const value = event.target.value;
    setSearchTerm(value);
    setCurrentPage(0); // Reiniciar currentPage solo si se ingresa un valor de búsqueda

    if (value === '') {
      setCurrentPage(lastPage);
      // Restaurar la última página visitada al vaciar el campo de búsqueda
    }
  };

  useEffect(() => {
    if (searchTerm === '') {
      setLastPage(currentPage); // Actualizar lastPage solo cuando no hay un término de búsqueda
    }

    const filteredContent = playlists.filter((playlists) =>
      playlists.title.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredPlaylist(filteredContent);

  }, [playlists, searchTerm, currentPage]);


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
        if (error.response && error.response.status === 409) {
          toast.error('Ya existe una playlist con el mismo título.', {
            autoClose: 800,
            closeButton: false,
          });
          clearLabels();
        } else {
          console.log(error);
        }
      });
  };

  const handlePlaylistClick = (playlistId) => {
    navigate(`/playlist/${playlistId}`);
  };

  return (
    <>
      <Navbar />
      <ToastContainer position="top-right" />
      <div className="hero text-center">
        <h1>¡Bienvenido!</h1>
        <p>Disfruta la música a tu manera</p>
      </div>
      <div className="row justify-content-center mb-4">
        <div className="col-4">
          <form className="mx-1 mx-md-4" onSubmit={handleSubmit}>
            <div className="flex column">
              <input
                type="text"
                className="form-control"
                id="title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
                placeholder="Titulo"
              />
              <input
                type="text"
                className="form-control mt-2"
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
                placeholder="Descripción"
              />
              <div className="container">
                <button type="submit" className="btn bg-black text-white btn-lg mt-2">
                  Crear
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
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
      <div className="row justify-content-center mb-4">
        <div className="col-8">
          <table className="table table-bordered">
            <thead className="thead-dark">
              <tr>
                <th>Titulo de playlist</th>
                <th>Descripción</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {filteredPlaylist.map((playlist) => (
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

export default Home;
