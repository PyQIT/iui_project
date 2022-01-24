import {axiosInstanceGet, axiosInstancePost} from 'core/axiosConfig';

const TRANSACTIONS_URL = '/transfers';

const getLockedTransactionsApi = () =>
  axiosInstanceGet({
      url: `${TRANSACTIONS_URL}/getLockedTransactions`,
  });

const getDetailsTransactionApi = idTransaction =>
  axiosInstanceGet({
    url: `${TRANSACTIONS_URL}/${idTransaction}`,
  });

const getTransactionsApi = () =>
    axiosInstanceGet({
        url: TRANSACTIONS_URL,
    });

const sendTransactionsApi = json =>
    axiosInstancePost({
        url: TRANSACTIONS_URL,
        data: json,
    });

const getLocksAmountApi = () =>
    axiosInstanceGet({
        url: `${TRANSACTIONS_URL}/getLocksAmount`,
    });


export {
    getLockedTransactionsApi,
    getTransactionsApi,
    getDetailsTransactionApi,
    sendTransactionsApi,
    getLocksAmountApi
};
