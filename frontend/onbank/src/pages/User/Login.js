import React from "react";
import { Link } from "react-router-dom";
// eslint-disable-next-line import/no-named-as-default
import Auth from "../../API/security/authentication"
import AuthUtil from "../../API/security/authenticationUtil"

const Login = () => {

    const onSubmit = (event) => {
        event.preventDefault();

        if (!AuthUtil.isLoggedIn()) {
            Auth.login(event.target[0].value, event.target[1].value).then(() => {
                console.log('Login success');
            });
        }


    };

    return (
        <div className="text-center">
            <form className="form-signin" onSubmit={onSubmit}>
                <input
                    type="text"
                    id="inputUsername"
                    className="form-control top-field"
                    placeholder="Login"
                    required
                />
                <input
                    type="password"
                    id="inputPassword"
                    className="form-control mb-3 bottom-field"
                    placeholder="Haslo"
                    required
                />
                <button className="btn btn-lg btn-primary btn-block" type="submit">
                    Zaloguj się
                </button>
                <p className="mt-3 mb-3 text-muted text-center">
                    Brak konta? <Link to="/auth/register">Stwórz nowe konto</Link>
                </p>
            </form>
        </div>
    );
};

export default Login;
