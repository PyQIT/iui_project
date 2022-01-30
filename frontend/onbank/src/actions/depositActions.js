import {
    createDepositApi,
    getDepositApi,
    getDepositBalanceApi
} from 'API/depositAPI';

export const NEW_DEPOSIT_REQUEST = 'NEW_DEPOSIT_REQUEST';
export const NEW_DEPOSIT_SUCCESS = 'NEW_DEPOSIT_SUCCESS';
export const NEW_DEPOSIT_FAILURE = 'NEW_DEPOSIT_FAILURE';
export const FETCH_DEPOSIT_SUCCESS = 'FETCH_DEPOSIT_SUCCESS';
export const FETCH_DEPOSIT_BALANCE_SUCCESS = 'FETCH_DEPOSIT_BALANCE_SUCCESS';

export const createDepositAction = data => dispatch => {
    const createDepositDto = {
        depositAmount: data.depositAmount,
        expectedReturn: data.expectedReturn,
    };

    dispatch({type: NEW_DEPOSIT_REQUEST});
    return createDepositApi(createDepositDto)
        .then(() => {
            dispatch({type: NEW_DEPOSIT_SUCCESS});
        })
        .catch(() => {
            dispatch({type: NEW_DEPOSIT_FAILURE});
        });
}

export const getDepositAction = name => dispatch => {
    return getDepositApi()
        .then(response => {
            dispatch({ type: FETCH_DEPOSIT_SUCCESS, payload: { name, data: response.data } });
        })
        .catch(err => console.log(err));
};

export const getDepositBalanceAction = () => dispatch => {
    return getDepositBalanceApi()
        .then(response => {
            dispatch({type: FETCH_DEPOSIT_BALANCE_SUCCESS, payload: {data: response.data}});
        })
        .catch(err => console.log(err));
};
