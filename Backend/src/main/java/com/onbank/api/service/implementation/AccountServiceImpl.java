package com.onbank.api.service.implementation;

import com.onbank.api.model.Account;
import com.onbank.api.repository.AccountRepository;
import com.onbank.api.service.AccountService;
import com.onbank.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account getAccountByNumber(String number) {
            return accountRepository.getAccountsByNumber(number);
    }

    @Override
    public Account getAccountByUserId(Long id) {
            return accountRepository.getAccountByUserId(id);
    }

    @Override
    public Account createAccount (Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Account id=" + id + " not found.")
        );
    }
}
