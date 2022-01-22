import React from 'react';
import { useStyles } from 'themes/loginTheme';

export default function Login() {
    const classes = useStyles();
    return(
        <div className={classes.login_wrapper}>
            <h1>Please Log In</h1>
            <form>
                <label>
                    <p>Username</p>
                    <input type="text" />
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" />
                </label>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    )
}