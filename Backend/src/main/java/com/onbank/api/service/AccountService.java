package com.onbank.api.service;

import com.onbank.api.model.Account;

public interface AccountService {
    Account getAccountByNumber(String number);

    Account getAccountByUserId(Long id);

    Account getAccountById(Long id);

    Account createAccount(Account account);

    Account getAccount();
}
