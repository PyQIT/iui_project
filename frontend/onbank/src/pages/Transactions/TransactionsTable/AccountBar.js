import React from 'react';
import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import currencyFormat from 'utils/CurrencyFormat';
import AccountNumberFormat from 'utils/AccountNumberFormat';
import { useStyles } from 'themes/accountBarTheme';

const AccountBar = ({ user }) => {
  const classes = useStyles();

  return (
    <div className={classes.wrapper}>
      <div className={classes.divNameAccount}>
        <Typography variant="h5" className={classes.nameAccount}>
            Konto standard
        </Typography>
        <Typography variant="h5" className={classes.numberAccount}>
          {AccountNumberFormat(user.accountNumber) || 'Brak numeru konta'}
        </Typography>
      </div>

      <div className={classes.divAvalaibleFunds}>
        <Typography variant="subtitle1" className={classes.avalaibleFundsLabel}>
          Dostępne środki
        </Typography>
        <Typography variant="h5" className={classes.avalaibleFunds}>
          {currencyFormat(user.accountBalance)}
        </Typography>
      </div>

      <div className={classes.divLock}>
        <Typography variant="subtitle1" className={classes.lock}>
          Blokady
        </Typography>
        <Typography variant="h5" className={classes.lock}>
          {currencyFormat(-1 * user.locksAmount)}
        </Typography>
      </div>
    </div>
  );
};

const mapStateToProps = ({ userProfile }) => {
    const user = userProfile;
  return { user };
};

export default connect(
  mapStateToProps
)(AccountBar);
