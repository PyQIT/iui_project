import React from 'react';
import Container from '@material-ui/core/Container';
import NavigationBar from 'core/NavigationBar';

function isMenu() {
    if (!(window.location.href.indexOf("login") > -1)) {
        return <NavigationBar/>
    }
}

const menu = isMenu();

const Template = ({ children }) => (
    <Container maxWidth="lg">
        {menu}
        {children}
    </Container>
);

export default Template;
