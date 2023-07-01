import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom'

function SingUp() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [agree, setAgree] = useState(false);

    const handleReset = () => {
        setUsername('');
        setEmail('');
        setPassword('');
        setAgree(false);
    };

    const handlePass = () => {
        setPassword('');
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const data = {
            username: username,
            email: email,
            password: password
        };

        // Password format validation
        const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&+=!¡¿?])[A-Za-z\d@#$%^&+=!¡¿?]{8,}$/;
        if (!passwordRegex.test(password)) {
            toast.error('La contraseña no cumple con el formato requerido.', {
                autoClose: 700,
                closeButton: false,
            });
            handlePass();
            return;
        }

        fetch('http://localhost:8080/auth/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Error en la respuesta de la petición.');
            })
            .then(data => {
                console.log(data);
                toast.success('Usuario creado', {
                    autoClose: 700,
                    closeButton: false,
                });
                navigate('/');
            })
            .catch(error => {
                console.log(error);
                toast.warning('El usuario ya existe!', {
                    autoClose: 700,
                    closeButton: false,
                });
                navigate('/singup');
                handleReset();
            });
    };



    return (
        <>
            <ToastContainer position="top-right" />
            <section class="vh-100">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-lg-12 col-xl-11">
                            <div class="card text-black">
                                <div class="card-body p-md-5">
                                    <div class="row justify-content-center">
                                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Registro</p>

                                            <form class="mx-1 mx-md-4" onSubmit={handleSubmit}>

                                                <div class="d-flex flex-row align-items-center mb-4">
                                                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline flex-fill mb-0">
                                                        <input type="text" class="form-control" id="username" value={username} onChange={(e) => setUsername(e.target.value)} required />
                                                        <label class="form-label" for="form3Example1c">nombre</label>
                                                    </div>
                                                </div>

                                                <div class="d-flex flex-row align-items-center mb-4">
                                                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline flex-fill mb-0">
                                                        <input type="email" class="form-control" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                                                        <label class="form-label" for="form3Example3c">Email</label>
                                                    </div>
                                                </div>

                                                <div class="d-flex flex-row align-items-center mb-4">
                                                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline flex-fill mb-0">
                                                        <input type="password" class="form-control" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                                                        <label class="form-label" for="form3Example4c">Password</label>
                                                    </div>
                                                </div>

                                                <div class="form-check d-flex justify-content-center">
                                                    <input
                                                        class="form-check-input mt-4"
                                                        type="checkbox"
                                                        value={agree}
                                                        id="form2Example3c"
                                                        checked={agree}
                                                        onChange={(e) => setAgree(e.target.checked)}
                                                    />
                                                    <label class="form-check-label mb-4" for="form2Example3">
                                                        I agree to sell my soul to the devil
                                                    </label>
                                                </div>

                                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                    <button type="submit" class="btn bg-black text-white btn-lg">Register</button>
                                                </div>

                                            </form>

                                        </div>
                                        <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                            <img src="https://ws.shoutcast.com/images/contacts/a/aa50/aa50319b-bc01-4d87-90c5-dabe0ce7af2a/radios/563858d1-165b-4c33-8249-3c74d3feaeed/563858d1-165b-4c33-8249-3c74d3feaeed.png"
                                                class="img-fluid" alt="prom" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </>



    );
}

export default SingUp;
