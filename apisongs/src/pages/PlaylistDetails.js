import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const PlaylistDetails = () => {
  const { playlistId } = useParams();
  const [playlist, setPlaylist] = useState({});
  const [songs, setSongs] = useState([]);
  const [duration, setDuration] = useState(0);
  const [allSongs, setAllSongs] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const token = localStorage.getItem('token');

  useEffect(() => {
    fetchPlaylistDetails();
    fetchAllSongs();
  }, []);

  const fetchPlaylistDetails = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/playlists/${playlistId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPlaylist(response.data?.playlist);
      setSongs(response.data?.songs);
      setDuration(response.data?.duration);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchAllSongs = async () => {
    try {
      const response = await axios.get('http://localhost:8080/songs', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params: {
          page: currentPage,
        },
      });
      setAllSongs(response.data?.content);
      setTotalPages(response.data?.totalPages);
    } catch (error) {
      console.error(error);
    }
  };

  const goToNextPage = () => {
    setCurrentPage((prevPage) => prevPage + 1);
  };

  const goToPreviousPage = () => {
    setCurrentPage((prevPage) => prevPage - 1);
  };

  const isLastPage = currentPage === totalPages - 1;

  useEffect(() => {
    fetchAllSongs();
  }, [currentPage]);

  const handleAddSong = (songCode) => {
    axios
      .post(
        `http://localhost:8080/playlists/${playlistId}`,
        { songCode },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        }
      )
      .then((response) => {
        if (response.status >= 200 && response.status < 300) {
          console.log(`Song with code ${songCode} added to the playlist`);
          alert(`Song with code ${songCode} added to the playlist`);
          fetchPlaylistDetails();
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 400) {
          console.log(`Error adding song with code ${songCode} to the playlist`);
          alert(`Error adding song with code ${songCode} to the playlist`);
        } else {
          console.error(error);
        }
      });
  };

  return (
    <>
      <div className='container'>
        
      </div>
      <div className="hero text-center">
        <h1 className="bg-black text-white">Playlist: {playlist.title}</h1>
        <p>Description: {playlist.description}</p>
      </div>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Titulo</th>
            <th>Duracion</th>
          </tr>
        </thead>
        <tbody>
          {songs.map((song) => (
            <tr key={song.id}>
              <td>{song.title}</td>
              <td>{song.duration}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div>
        <h3>Duracion de la playlist: {duration}</h3>
      </div>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Titulo</th>
            <th>Duracion</th>
            <th>Agregar</th>
          </tr>
        </thead>
        <tbody>
          {allSongs.map((song) => (
            <tr key={song.code}>
              <td>{song.title}</td>
              <td>{song.duration}</td>
              <td>
                <button className="btn btn-primary" onClick={() => handleAddSong(song.code)}>
                  Agregar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
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

export default PlaylistDetails;
