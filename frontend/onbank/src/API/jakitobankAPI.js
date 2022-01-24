import {axiosInstanceJTB} from 'core/axiosConfig';

const API_KEY = '479a98885127b9d23412cacfe6116356f425241b'

/*const getIBANDataApi = number => {
  return axiosInstanceJTB({
    params: { api_key: API_KEY},
    url: `${number}`,
  });
};*/

const getIBANDataApi = number => {
  return axiosInstanceJTB({
    data: {api_key: API_KEY, number: number},

  });
};

export {getIBANDataApi};
