import {
    NEW_DEPOSIT_REQUEST,
    NEW_DEPOSIT_SUCCESS,
    NEW_DEPOSIT_FAILURE,
    FETCH_DEPOSIT_SUCCESS,
    FETCH_DEPOSIT_BALANCE_SUCCESS
} from "../actions/depositActions";

const depositReducer = (state = '', action) => {
    switch (action.type) {
        case FETCH_DEPOSIT_SUCCESS:
            return {
                ...state,
                [action.payload.name]: action.payload.data,
            };
        case FETCH_DEPOSIT_BALANCE_SUCCESS:
            return {
                ...state,
                [action.payload.name]: action.payload.data,
            };
        case NEW_DEPOSIT_REQUEST:
            return {
                ...state,
                isLoading: true,
            };
        case NEW_DEPOSIT_SUCCESS:
            return {
                ...state,
                isLoading: false,
                isSuccess: true,
            };
        case NEW_DEPOSIT_FAILURE:
            return {
                ...state,
                isLoading: false,
                isSuccess: false,
            };
        default:
            return state;
    }
};

export default depositReducer;