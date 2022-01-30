import { FETCH_USER_PROFILE_SUCCESS } from 'actions/userProfileActions';

const userProfileReducer = (state = '', action) => {
  switch (action.type) {
    case FETCH_USER_PROFILE_SUCCESS:
      return {
        userID: action.payload.data.id,
        name: action.payload.data.name,
        surname: action.payload.data.surname,
        accountNumber: action.payload.data.accountNumber,
        accountBalance: action.payload.data.accountBalance,
        locksAmount: action.payload.data.locksAmount,
      };
    default:
      return state;
  }
};

export default userProfileReducer;
