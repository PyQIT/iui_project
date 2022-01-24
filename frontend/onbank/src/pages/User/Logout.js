import React from "react";
import {useHistory} from "react-router-dom";
import '../../themes/logout.css';
// eslint-disable-next-line import/no-named-as-default
import Auth from "../../API/security/authentication"
import AuthUtil from "../../API/security/authenticationUtil"


const Logout = () => {

    const history = useHistory();

    const onSubmitLogout = (event) => {
        event.preventDefault();

        if (AuthUtil.isLoggedIn()) {
            Auth.logout();
            console.log("Wylogano");
            history.push("/login");
        }
    };

    return (
        <div className="text-center">
                <form onSubmit={onSubmitLogout}>
                    <button className="logoutButton" type="submit">
                        Wyloguj sie
                    </button>
                </form>
        </div>
    );
};

export default Logout;
