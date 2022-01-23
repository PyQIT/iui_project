import React from "react";
import { Link, useHistory, Redirect } from "react-router-dom";


const Register = () => {
    const client = useClient();
    const history = useHistory();
    const { customer, setCustomer } = useCustomer();

    const onSubmit = (event) => {
        event.preventDefault();

        const [username, password] = event.target.elements;

        client
            .request(register, {
                username: username.value,
                password: password.value,
            })
            .then(({ register_customer: { customer, token } }) => {
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
        <form onSubmit={onSubmit} className="form-signin">
            <h1 className="h3 mb-3 font-weight-normal text-center">
                Rejestracja
            </h1>
            <label htmlFor="inputUsername" className="sr-only">
                Login
            </label>
            <input
                type="email"
                id="inputUsername"
                className="form-control middle-field"
                placeholder="Login"
                required
            />
            <label htmlFor="inputPassword" className="sr-only">
                Haslo
            </label>
            <input
                type="password"
                id="inputPassword"
                className="form-control mb-3 bottom-field"
                placeholder="Haslo"
                required
            />
            <button className="btn btn-lg btn-primary btn-block" type="submit">
                Utwórz konto
            </button>
            <p className="mt-3 mb-3 text-muted text-center">
                Posiadasz konto ? <Link to="/auth/signin">Zaloguj sie tutaj</Link>
            </p>
        </form>
    );
};

export default Register;