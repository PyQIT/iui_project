import axios from 'axios';
import { toast } from 'react-toastify';
import AuthUtil from '../API/security/authenticationUtil'

const baseURL = 'http://localhost:8080/api/';
const jakitobankURL = 'https://api.ibanapi.com/v1/validate/';

const axiosInstanceGet = axios.create({ baseURL: baseURL, method: "GET" });
const axiosInstancePost = axios.create({ baseURL, method: "POST" });

const axiosInstanceJTB = axios.create({ baseURL: jakitobankURL, method: "POST"});


const requestHandler = request => {
  if (AuthUtil.isLoggedIn()) {
    request.headers.Authorization = `Bearer ${  AuthUtil.getAuthToken()}`;
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
axiosInstancePost.interceptors.response.use(response => {toast.success('Sukces');return response}, error => errorHandler(error));

axiosInstanceJTB.interceptors.request.use(request => requestHandler(request));
axiosInstanceJTB.interceptors.response.use(response => response, error => errorHandler(error));

export { axiosInstanceGet, axiosInstancePost, axiosInstanceJTB };
