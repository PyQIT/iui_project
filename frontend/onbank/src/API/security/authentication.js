import {toast} from 'react-toastify';
import {authenticationRequest} from "../authenticationAPI";
import AuthUtil from "./authenticationUtil";

class Auth {

    login(username, password) {
        console.log(username);
        console.log(password);
        if (!AuthUtil.isLoggedIn()) {
            return authenticationRequest(username, password)
                .then(response => {
                    if (response.status === 200) {
                        localStorage.setItem('jwttoken', JSON.stringify(response.data.jwttoken));
                    }
                })
                .catch(err => {
                    console.log(err);
                    if (err.response.status === 401) {
                        toast.error('Niepoprawne dane logowania');
                    } else {
                        toast.error('Wystąpił nieznany błąd');
                    }
            });
        }
        return undefined;
    }
}

export default new Auth();