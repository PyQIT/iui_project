import {axiosInstanceGet} from 'core/axiosConfig';

export const USER_PROFILE_URL = '/getProfileUser';

const getUserProfileApi = () =>
    axiosInstanceGet({
        url: USER_PROFILE_URL
    });

export { getUserProfileApi };
