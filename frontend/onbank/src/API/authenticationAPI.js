import {axiosInstancePost} from 'core/axiosConfig';

const authenticationRequest = (username, password) =>
    axiosInstancePost({
        method: 'POST',
        url: '/authenticate',
        data: {
            username: username,
            password: password
        },
    });

export {
    authenticationRequest,
};