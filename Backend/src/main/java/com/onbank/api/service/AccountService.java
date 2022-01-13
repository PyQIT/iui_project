package com.onbank.api.service;

import com.onbank.api.dto.AccountRegistrationDto;
import com.onbank.api.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account getAccountByNumber(String number);
    Account getAccountByUserId(Long id);
    Account createAccount(Account account);
    Account getAccountById(Long id);
    Account findByUsername(String username);
    Account save(AccountRegistrationDto registration);
}
