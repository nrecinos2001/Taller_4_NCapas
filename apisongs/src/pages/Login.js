import { useState } from 'react'

function Login() {

    const [credentials, setCredentials] = useState({})

    const handleChange = (e) => {
        setCredentials({
            ...credentials,
            [e.target.name]: e.target.value

        })
    }

    const handleLogin = () => {
        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(result => {
                const token = result.token;
                //put token in local storage
                localStorage.setItem('key', token);
            })
            .catch(error => {
                console.log('Error al realizar la solicitud:', error);
            });
    }

    return (

        <section class="vh-100 gradient-custom">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card bg-dark text-white" >
                            <div class="card-body p-5 text-center">

                                <div class="mb-md-5 mt-md-4 pb-5">

                                    <h2 class="fw-bold mb-2 text-uppercase">Login</h2>

                                    <div class="form-outline form-white mb-4">
                                        <input type="text" name="identificator" class="form-control form-control-lg" onChange={handleChange} />
                                        <label class="form-label" for="typeEmailX">USERNAME</label>
                                    </div>

                                    <div class="form-outline form-white mb-4">
                                        <input type="password" name="password" class="form-control form-control-lg" onChange={handleChange} />
                                        <label class="form-label" for="typePasswordX">PASSWORD</label>
                                    </div>

                                    <button class="btn btn-outline-light btn-lg px-5" type="submit" onClick={handleLogin}>Login</button>
                                </div>

                                <div>
                                    <p class="mb-0">Don't have an account? <a href="#!" class="text-white-50 fw-bold">Sign Up</a>
                                    </p>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


    );
}

export default Login