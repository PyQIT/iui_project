import React from "react";
import {Link, useHistory} from "react-router-dom";
import '../../themes/login.css';
// eslint-disable-next-line import/no-named-as-default
import Auth from "../../API/security/authentication"
import AuthUtil from "../../API/security/authenticationUtil"
import {Typography} from "@material-ui/core";

const Login = () => {

    const history = useHistory();

    const onSubmit = (event) => {
        event.preventDefault();

        if (!AuthUtil.isLoggedIn()) {
            Auth.login(event.target[0].value, event.target[1].value).then(() => {
                console.log('Login success');
                history.push("/transactions/completed-transactions");
            });
        }


    };

    return (
        <div className="container">
            <form className="form-signin" onSubmit={onSubmit}>
                <Typography variant="h6" className="on">
                    ON
                </Typography>
                <Typography variant="h6" className="title">
                    BANK
                </Typography>
                <h3>Logowanie</h3>
                <p>
                <input
                    type="text"
                    id="inputUsername"
                    className="form-control top-field"
                    placeholder="Login"
                    required
                />
                </p>
                <p>
                <input
                    type="password"
                    id="inputPassword"
                    className="form-control mb-3 bottom-field"
                    placeholder="Haslo"
                    required
                />
                    </p>
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
