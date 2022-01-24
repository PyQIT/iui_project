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
                <h1 className="h3 mb-3 font-weight-normal">Please sign in</h1>
                <label htmlFor="inputUsername" className="sr-only">
                    Login
                </label>
                <input
                    type="text"
                    id="inputUsername"
                    className="form-control top-field"
                    placeholder="Login"
                    required
                />
                <label htmlFor="inputPassword" className="sr-only">
                    Password
                </label>
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
