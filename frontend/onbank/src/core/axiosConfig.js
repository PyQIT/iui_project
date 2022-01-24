import axios from 'axios';
import { toast } from 'react-toastify';
import AuthUtil from '../API/security/authenticationUtil'

const baseURL = 'http://localhost:8080/api/';
const jakitobankURL = 'http://www.jakitobank.pl/api/';

const axiosInstanceGet = axios.create({ baseURL });
const axiosInstancePost = axios.create({ baseURL });

const axiosInstanceJTB = axios.create({ baseURL: jakitobankURL });

// const accessToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTY0Mjk4MDM3OCwiaWF0IjoxNjQyOTYyMzc4fQ.Iobjm6pNpdufSb6nyIOeArqe_FOyW8NI6ohUdk6Ml2Gw8nkZISPa8D1F-B_tpcddlXQQ0sACKwtlCY_3qTgdRQ';


const requestHandler = request => {
  if (AuthUtil.isLoggedIn()) {
    request.headers.Authorization = AuthUtil.getAuthToken();
    console.log(request);
  }
  return request;
};

const errorHandler = error => {
  if (!error.response) {
    toast.error('Sprawdź połączenie z internetem');
  } else if (error.response) {
    toast.error(
      `${error.response.data.code || 'Kod błędu'} - ${error.response.data.description ||
        'Komunikat błędu'}`,
    );
  }
  return Promise.reject(error);
};

axiosInstanceGet.interceptors.request.use(request => requestHandler(request));
axiosInstanceGet.interceptors.response.use(response => response, error => errorHandler(error));

axiosInstancePost.interceptors.request.use(request => requestHandler(request));
axiosInstancePost.interceptors.response.use(response => {toast.success('Utworzono');return response}, error => errorHandler(error));

axiosInstanceJTB.interceptors.request.use(request => requestHandler(request));
axiosInstanceJTB.interceptors.response.use(response => response, error => errorHandler(error));

export { axiosInstanceGet, axiosInstancePost, axiosInstanceJTB };
