import React, { useState, useEffect } from 'react';

function Home() {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [playlists, setPlaylists] = useState([]);

    const token = localStorage.getItem('token');
    const handleSubmit = (e) => {
        e.preventDefault();

        const data = {
            title: title,
            description: description,
        };

        fetch('http://localhost:8080/playlists/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}` // Incluye el token en los encabezados
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    setTitle('');
                    setDescription('');
                    return response.json(),
                    fetchPlaylists(); // Llama a fetchPlaylists después de crear la playlist exitosamente
                }
                throw new Error(response.statusText);
            })
            .then(() => {
                alert('Playlist creada con éxito');
            })
            .catch(error => {
                console.log(error);
                // Manejar el error en caso de que ocurra
            });
    };




    const fetchPlaylists = () => {
        const url = 'http://localhost:8080/playlists/user';
        fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta de la API');
                }
                return response.json();
            })
            .then(data => {
                // Guardar los datos en el estado
                setPlaylists(data?.content);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    };


    useEffect(() => {
        fetchPlaylists();
    }, []);

    return (
        <>
            <div className="hero text-center">
                <h1>¡Bienvenido!</h1>
                <p>Disfruta la música a tu manera</p>
            </div>
            <div className="container">
                <form className="mx-1 mx-md-4" onSubmit={handleSubmit}>
                    <div className="flex column">
                        <input type="text" className="form-control" id="title" value={title} onChange={(e) => setTitle(e.target.value)} required placeholder="Titulo" />
                        <input type="text" className="form-control" id="description" value={description} onChange={(e) => setDescription(e.target.value)} required placeholder="Descripción" />
                        <div className="container">
                            <button type="submit" className="btn bg-black text-white btn-lg">Crear</button>
                        </div>
                    </div>
                </form>
            </div>
            <table className="table table-striped table-bordered">
                <thead>
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
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default Home;
