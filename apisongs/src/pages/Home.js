import React, { useState, useEffect } from 'react';
const token = localStorage.getItem('token');

function Home() {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

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
                    return response.json();
                }
                throw new Error(response.status);
            })
            .then(data => {
                console.log(data);
                console.log('Playlist creada con éxito.');
                // Realizar las acciones adicionales necesarias después de un registro exitoso
            })
            .catch(error => {
                console.log(error);
                // Manejar el error en caso de que ocurra
            });
    };


    return (
        <>
            <div className="hero text-center">
                <h1>¡Bienvenido!</h1>
                <p>Disfruta la musica a tu manera</p>
            </div>
            <div class="container">
                <form class="mx-1 mx-md-4" onSubmit={handleSubmit}>
                    <div class="flex column">
                        <input type="text" class="form-control" id="title" value={title} onChange={(e) => setTitle(e.target.value)} required placeholder="Titulo" />
                        <input type="text" class="form-control" id="description" value={description} onChange={(e) => setDescription(e.target.value)} required placeholder="Descripcion" />
                        <div class="container ">
                        <button type="submit" class="btn bg-black text-white btn-lg">Crear</button>
                        </div>
                    </div>

                </form>
            </div>
            <table className="table table-striped table-bordered ">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nombre</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr >
                        <td>1</td>
                        <td>ano</td>
                        <td>
                            <a>Ver playlist</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </>
    )
}

export default Home
