import React from 'react';
import {Provider} from 'react-redux';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {ThemeProvider} from '@material-ui/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import {MuiPickersUtilsProvider} from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import store from 'store';
import {routes} from 'routes';
import RouteWithSubRoutes from 'routes/RouteWithSubRoutes';
import customTheme from 'themes/customTheme';
import Template from 'core/Template/Template';
import Login from "./pages/User/Login";
import PrivateRoute from './routes/PrivateRoute'


const App = () => (
  <Provider store={store}>
    <ThemeProvider theme={customTheme}>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <>
                <ToastContainer/>
                <CssBaseline/>
                <Router path="/">
                    <Route path="/login" component={Login}/>
                    <PrivateRoute
                        path="/"
                        render={() => {
                            return (
                                <Template>
                                    <Switch>
                                        {routes.map(route => (
                                            <RouteWithSubRoutes key={route.component} {...route} />
                                        ))}
                                    </Switch>
                                </Template>
                            );
                        }}
                    />
                </Router>
            </>
        </MuiPickersUtilsProvider>
    </ThemeProvider>
  </Provider>
);

export default App;
