import { axiosInstanceJTB } from 'core/axiosConfig';

const getIBANDataApi = number => {
  return axiosInstanceJTB({
    params: { numer: number },
  });
};

export { getIBANDataApi };
