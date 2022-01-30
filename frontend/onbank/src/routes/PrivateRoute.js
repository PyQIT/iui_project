import React from 'react'
import AuthUtil from "../API/security/authenticationUtil";
import {Redirect, Route} from 'react-router-dom'
import {paths} from "./paths";

const PrivateRoute = ({render: Render, ...rest}) => {

    return (
        <Route
            {...rest}
            render={props =>
                AuthUtil.isLoggedIn() ? (
                    <Render {...props} />
                ) : (
                    <Redirect to={{pathname: paths.login/*, state: { from: props.location }*/ }}/>
                )
            }
        />
    )
}

export default PrivateRoute