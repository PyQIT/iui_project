class AuthUtil {
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
            return JSON.parse(localStorage.getItem('jwttoken'));
        }
        return undefined;
    }
}

export default new AuthUtil();