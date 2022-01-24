package com.onbank.api.controller;

import com.onbank.api.dto.AccountDto;
import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import com.onbank.api.service.AccountService;
import com.onbank.api.transformer.AccountTransformer;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Validated
public class AccountController {
    private final AccountService accountService;
    private final AuthUser authUser;

    @GetMapping("/number/{number}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountByNumber(@PathVariable String number) {
        return AccountTransformer.convertToDto(accountService.getAccountByNumber(number));
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountByUser(@PathVariable Long id) {
        return AccountTransformer.convertToDto(accountService.getAccountByUserId(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createAccount(@Valid @RequestBody AccountDto accountDto) {
        accountService.createAccount(AccountTransformer.convertToEntity(accountDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccountById(@PathVariable Long id){
        return AccountTransformer.convertToDto(accountService.getAccountById(id));
    }





    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getAccount() {
        return AccountTransformer.convertToDto(accountService.getAccount());
    }

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(){
        return accountService.getAccount().getAccountBalance();
    }

}
