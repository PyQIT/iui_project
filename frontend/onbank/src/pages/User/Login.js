import React from "react";
import { Link, Redirect, useHistory } from "react-router-dom";

import { useCustomer, useClient } from "../../store";

const Login = () => {
    const client = useClient();
    const history = useHistory();
    const { customer, setCustomer } = useCustomer();

    const onSubmit = (event) => {
        event.preventDefault();

        const [username, password] = event.target.elements;

        client
            .request(login, {
                username: username.value,
                password: password.value,
            })
            .then(({ login_customer: { customer, token } }) => {
                client.setHeader("authorization", `Bearer ${token}`);

                setCustomer(customer);

                history.push("/");
            })
            .catch(console.log);
    };

    if (customer) {
        return <Redirect to="/" />;
    }

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