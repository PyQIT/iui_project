import React, {useEffect, useState} from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { Formik, Form } from 'formik';
import { MuiThemeProvider } from '@material-ui/core/styles';
import {
    Button,
    CircularProgress,
    FormControl,
    FormHelperText,
    InputLabel,
    Paper,
} from '@material-ui/core';
import { paths } from 'routes/paths';
import { sendTransactionsAction, setIsSuccessAction } from 'actions/transactionsActions';
import { useStyles } from 'themes/newDepositTheme';
import { colorthemeButtonAndDate } from 'themes/customTheme';
import InputRange from 'react-input-range';
import { newTransferSchema } from './newDepositSchema';
import 'react-input-range/lib/css/index.css';


const NewDeposit = ({ sendTransactions, isLoading, isSuccess, setIsSuccess }) => {
    const classes = useStyles();
    const [setBankName] = useState(null);
    let depositAmount = 0;

        return (
            <Paper className={classes.root}>
                <Formik
                    initialValues={{
                        receiver: '',
                        accountNumber: '',
                        description: '',
                        amount: '',
                        date: new Date(),
                        saveReceiver: false,
                    }}
                    validationSchema={() => newTransferSchema(setBankName)}
                    validateOnBlur
                    validateOnChange
                    onSubmit={values => {
                        sendTransactions(values);
                    }}
                >
                    {({errors, touched, handleChange, handleBlur, values}) => (
                        <Form className={classes.form}>
                            <div className={classes.inputs}>


                                <div className={classes.inputInRange}>
                                    <FormControl

                                        className={classes.textFieldAmount}
                                        error={!!(errors.amount && touched.amount)}
                                    >
                                        <InputRange
                                            maxValue={values.amount}
                                            minValue={0}
                                            value={depositAmount}
                                            onChange={handleChange(depositAmount)}
                                            onBlur={handleBlur(depositAmount)}
                                        />
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
                                        <InputLabel htmlFor="amountInput">Zwrot lokaty po roku</InputLabel>
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
                                            <Redirect to={paths.transactions}/>
                                            {setIsSuccess(false)}
                                        </>
                                    ) : null}
                                </div>
                            </div>
                        </Form>
                    )}
                </Formik>
            </Paper>
        );
};


const mapStateToProps = ({ transactions }) => {
    const { isLoading, isSuccess } = transactions;
    return { isLoading, isSuccess };
};

const mapDispatchToProps = dispatch => {
    return {
        sendTransactions: values => {
            dispatch(sendTransactionsAction(values));
        },
        setIsSuccess: status => {
            dispatch(setIsSuccessAction(status));
        },
    };
};

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(NewDeposit);
