package com.onbank.api.service;

import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;

public interface DepositService {
    Deposit createDeposit(Deposit deposit);
    Deposit getDeposit(Long id);
    Deposit getDepositByAccount(Account account);
    Deposit getDeposit();
}
