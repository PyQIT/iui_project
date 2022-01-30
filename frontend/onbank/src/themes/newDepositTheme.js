import { makeStyles } from '@material-ui/core/styles';
export const useStyles = makeStyles(theme => ({
    root: {
        height: 'auto',
        marginTop: 10,
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
    amoutSlider:{
        width: '100% !important',
        backgroundColor: '#27ae6059 !important'
    },
    recieverInput: {
        marginBottom: 20,
        marginTop: 20,
        width: '100%',
        marginLeft: '0%',
    },
    inputs:{
        minWidth: "40%",
    },
    inputStyle:{
        margin: 'auto auto auto',
        width: "100%",
    },
    inputInRange:{
        display: 'flex',
        paddingTop: 25,
    },
    textFieldAmount:{
        width: '100%',
    },
    buttonDiv: {
        display: 'flex',
        justifyContent: 'flex-end',
        marginBottom: 20,
    },
    inputWidth: {
        width: 450,
        [theme.breakpoints.down('sm')]: {
            width: 280,
        },
    },
    icon: {
        color: '#707070',
        '&:hover': {
            backgroundColor: '#E3E5E1',
            borderRadius: 10,
        },
    },
    result: {
        fontWeight: 600,
        color: '#27ae60',
        display: 'inline',
    },

    depositContainer: {
        display: 'flex',
    },
    depositResult: {
        margin: 'auto',
        padding: 25,
    },
}));