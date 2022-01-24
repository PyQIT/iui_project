import React from "react";
import { Link, Redirect, useHistory } from "react-router-dom";
// eslint-disable-next-line import/no-named-as-default
import Auth from "../../API/security/authentication"

const Login = () => {


    const onSubmit = (event) => {
        event.preventDefault();

        const [username, password] = event.target.elements;

        Auth.login(username, password);
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
