import React, {useState} from 'react';
import {connect} from 'react-redux';
import {Redirect} from 'react-router-dom';
import {Form, Formik} from 'formik';
import {MuiThemeProvider} from '@material-ui/core/styles';
import {Button, CircularProgress, FormControl, FormHelperText, InputLabel, Paper,} from '@material-ui/core';
import {paths} from 'routes/paths';
import {useStyles} from 'themes/newDepositTheme';
import {colorthemeButtonAndDate} from 'themes/customTheme';
import Slider from 'react-input-slider';
import 'react-input-range/lib/css/index.css';
import {createDepositAction, getDepositAction} from "../../actions/depositActions";
import {getUserProfileAction} from "../../actions/userProfileActions";
import {getDepositBareApi} from "../../API/depositAPI";


const NewDeposit = ({createDeposit, isLoading, isSuccess, setIsSuccess, getUserProfile, getDeposit}) => {
    const classes = useStyles();
    const [state, setState] = useState({x: 10, wynik: 16});


    var depositData = {};
    var hasDeposit = false;

    const checkDeposit = () => {
        getDepositBareApi()
            .then(response => {
                if (response.status === 204) {
                    hasDeposit = false;
                } else {
                    depositData = response.data;
                    hasDeposit = true;
                    console.log(response)
                }
            })
            .catch(error => {
                hasDeposit = false;
                console.log(error);
            });
    }

    return (
        <Paper className={classes.root}>
            <Formik
                initialValues={{
                    x: '',
                    wynik: '',
                }}
                onSubmit={() => {
                    createDeposit({
                        depositAmount: state.x.toFixed(2),
                        expectedReturn: state.wynik.toFixed(2)
                        });
                        getUserProfile();
                    }}
                >
                    {({errors, touched}) => (
                        <Form className={classes.form}>
                            <div className={classes.inputs}>


                                <div className={classes.inputInRange}>
                                    <FormControl

                                        className={classes.textFieldAmount}
                                        error={!!(errors.amount && touched.amount)}
                                    >
                                        <Slider className={classes.amoutSlider}
                                                axis="x"
                                                x={state.x}
                                                xmin={1}
                                                xmax={10000}
                                                onChange={({x}) => setState(state => ({...state, x}))}
                                                styles={{
                                                    active: {
                                                        backgroundColor: '#27AE60'
                                                    },
                                                    disabled: {
                                                        opacity: 0.5
                                                    }
                                                }}
                                        />
                                        <h3>
                                            <center>Kwota: {state.x}</center>
                                        </h3>

                                        {errors.amount && touched.amount ? (
                                            <FormHelperText id="amount-error-text">{errors.amount}</FormHelperText>
                                        ) : null}
                                    </FormControl>
                                </div>


                                <div className={classes.inputStyle}>
                                    <FormControl
                                        className={classes.textFieldAmount}
                                        error={!!(errors.amount && touched.amount)}
                                    >
                                        <InputLabel htmlFor="amountInput">Zwrot lokaty po roku: <div
                                            className={classes.result}>{state.wynik = (106 * state.x) / 100}</div>
                                        </InputLabel>
                                    </FormControl>
                                </div>


                                <div className={classes.buttonDiv}>
                                    {isLoading ? (
                                        <CircularProgress size={30}/>
                                    ) : (
                                        <MuiThemeProvider theme={colorthemeButtonAndDate}>
                                            <Button variant="contained" color="primary" type="submit">
                                                Wyślij
                                            </Button>
                                        </MuiThemeProvider>
                                    )}
                                    {isSuccess ? (
                                        <>
                                            <Redirect to={paths.newDeposit}/>
                                            {setIsSuccess(false)}
                                        </>
                                    ) : null}
                                </div>
                            </div>
                        </Form>
                    )}
                </Formik>

                <div className={classes.depositContainer}>
                    <div className={classes.depositResult}>
                        {/* eslint-disable-next-line react/button-has-type */}
                        <button onClick={() => {
                            checkDeposit()
                        }}>Odbierz
                        </button>
                    </div>
                </div>

        </Paper>
        );
};


const mapStateToProps = ({ transactions }) => {
    const { isLoading, isSuccess } = transactions;
    return { isLoading, isSuccess };
};

const mapDispatchToProps = dispatch => {
    return {
        createDeposit: values => {
            dispatch(createDepositAction(values));
        },
        getDeposit: () => {
            dispatch(getDepositAction());
        },
        getUserProfile: () => {
            dispatch(getUserProfileAction());
        },
    };
};

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(NewDeposit);
