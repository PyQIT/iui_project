class AuthUtil {
    isLoggedIn() {
        return !!localStorage.getItem('jwttoken');
    }

    getAuthToken() {
        if (this.isLoggedIn()) {
            return JSON.parse(localStorage.getItem('jwttoken'));
        }
        return undefined;
    }
}

export default new AuthUtil();