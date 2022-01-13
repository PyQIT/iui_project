package com.onbank.api.service.implementation;

import com.onbank.api.dto.AccountRegistrationDto;
import com.onbank.api.model.Account;
import com.onbank.api.repository.AccountRepository;
import com.onbank.api.service.AccountService;
import com.onbank.exceptions.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account save(AccountRegistrationDto registration) {
        Account account = new Account();
        throw new NotImplementedException("Creating new user account");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

    }
}
