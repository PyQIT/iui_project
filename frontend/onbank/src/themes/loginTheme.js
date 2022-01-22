import { makeStyles } from '@material-ui/core/styles';
export const useStyles = makeStyles(theme => ({
    login_wrapper: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    form: {
        width: '100%',
        height: 'auto',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        [theme.breakpoints.down('md')]: {
            width: '100%',
            display: 'flex',
            flexDirection: 'column',
        },
    },
}));