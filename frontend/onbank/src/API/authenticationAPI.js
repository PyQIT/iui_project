import {axiosInstancePost} from 'core/axiosConfig';

const authenticationRequest = (username, password) =>
    axiosInstancePost({
        url: '/authenticate',
        data: {
            username: username,
            password: password
        },
    });

export {
    authenticationRequest,
};