import { createStore, combineReducers, compose } from 'redux';
import user from './reducers/friends';
import friends from './reducers/friends';

const rootReducer = combineReducers({
    user,
    friends
});

let composeEnhancers = compose;

if (__DEV__) {
    composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
}

const configureStore = () => {
    return createStore(rootReducer, composeEnhancers());
};

export default configureStore;