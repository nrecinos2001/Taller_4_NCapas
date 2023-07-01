import { useState } from 'react';
import { useSnackbar } from 'notistack';
import { Outlet, Link, useNavigate } from 'react-router-dom';


function Login() {
    const [credentials, setCredentials] = useState({});
    const { enqueueSnackbar } = useSnackbar();
    const [disableLogin, setDisableLogin] = useState(true);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;

        setCredentials((prevCredentials) => ({
            ...prevCredentials,
            [name]: value
        }));

        const { identificator = '', password = '' } = {
            ...credentials,
            [name]: value
        };
        if (identificator.trim() !== '' && password.trim() !== '') {
            setDisableLogin(false);
        } else {
            setDisableLogin(true);
        }
    };

    const handleLogin = () => {
        fetch('http://localhost:8080/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(credentials)
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then((result) => {
                const token = result.token;
                // guardar el token en localStorage
                localStorage.setItem('token', token);

                // Redirigir al usuario a la página de Dashboard
                navigate('/home');
            })
            .catch((error) => {
                console.log('Error al realizar la solicitud:', error);
                enqueueSnackbar('Ocurrió un error en la solicitud', { variant: 'error' });
            });
    };

    return (
        <section className="vh-100 gradient-custom">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div className="card bg-dark text-white">
                            <div className="card-body p-5 text-center">
                                <form>
                                    <div className="mb-md-5 mt-md-4 pb-5">
                                        <h2 className="fw-bold mb-2 text-uppercase">Login</h2>

                                        <div className="form-outline form-white mb-4">
                                            <input type="text" required name="identificator" className="form-control form-control-lg" onChange={handleChange} />
                                            <label className="form-label" htmlFor="typeEmailX">USERNAME</label>
                                        </div>

                                        <div className="form-outline form-white mb-4">
                                            <input type="password" name="password" className="form-control form-control-lg" onChange={handleChange} />
                                            <label className="form-label" htmlFor="typePasswordX">PASSWORD</label>
                                        </div>

                                        <Link to={!disableLogin ? "/home" : "#"}>
                                            <button
                                                className="btn btn-outline-light btn-lg px-5"
                                                type="submit"
                                                onClick={handleLogin}
                                                disabled={disableLogin}
                                            >
                                                Login
                                            </button>
                                        </Link>


                                    </div>
                                </form>

                                <div>
                                    <Link to="/singup"><p className="mb-0">Don't have an account? <a href="#!" className="text-white-50 fw-bold">Sign Up</a></p>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Outlet />
        </section>

    );
}

export default Login;