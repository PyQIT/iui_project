import React from "react";
// eslint-disable-next-line import/no-named-as-default
import Auth from "../../API/security/authentication"
import AuthUtil from "../../API/security/authenticationUtil"

const Logout = () => {

    const onSubmitLogout = (event) => {
        event.preventDefault();

        if (!AuthUtil.isLoggedIn()) {
            Auth.logout();
            console.log("Wylogano");
        }
    };

    return (
        <div className="text-center">
                <form className="form-signin" onSubmit={onSubmitLogout}>
                    <button className="btn btn-lg btn-primary btn-block" type="submit">
                        Wyloguj sie
                    </button>
                </form>
        </div>
    );
};

export default Logout;
