import {toast} from 'react-toastify';
import {authenticationRequest} from "../authenticationAPI";

class Auth {

    login(username, password) {
        if (!this.isLoggedIn()) {
            return authenticationRequest(username, password)
                .then(response => {
                    if (response.status === 200) {
                        localStorage.setItem('jwttoken', JSON.stringify(response.data.jwttoken));
                    }
                }).catch(err => {
                    if (err.request.response.status === 401) {
                        toast.error('Niepoprawne dane logowania');
                    } else {
                        toast.error('Wystąpił nieznany błąd');
                    }
            });
        }
        return undefined;
    }

    isLoggedIn() {
        return !!localStorage.getItem('jwttoken');
    }

    logout() {
        if (this.isLoggedIn()){
            localStorage.removeItem('jwttoken');
        }
    }

    getAuthToken() {
        if (this.isLoggedIn()) {
            return localStorage.getItem('jwttoken');
        }
        return undefined;
    }

}

export default new Auth();