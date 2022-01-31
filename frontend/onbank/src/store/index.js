import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import transactions from 'reducers/transactionsReducer';
import deposit from 'reducers/depositReducer';
import userProfile from 'reducers/userProfileReducer';
import operationType from 'reducers/operationTypeReducer';
import { loadState, saveState } from 'store/localStorage';

const persistedState = loadState();

const store = createStore(
  combineReducers({ userProfile, transactions, deposit, operationType }),
  persistedState,
  applyMiddleware(thunk),
);

store.subscribe(() => {
  saveState({
    userProfile: store.getState().userProfile,
  });
});

export default store;
