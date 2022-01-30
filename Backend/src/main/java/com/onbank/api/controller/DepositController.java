package com.onbank.api.controller;

import com.onbank.api.dto.DepositDto;
import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import com.onbank.api.service.DepositService;
import com.onbank.api.service.UserService;
import com.onbank.api.transformer.DepositTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deposits")
@Validated
public class DepositController {
    private final DepositService depositService;
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getDepositByAccount(@RequestBody Account account) {
        return DepositTransformer.convertToDto(depositService.getDepositByAccount(account));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createDeposit(@Valid @RequestBody DepositDto depositDto) {
        //TODO: Potrzebna walidacja!!!!!! Dane tworzącego powinny weryfikowane lub uzupełniane automatycznie
        depositService.createDeposit(DepositTransformer.convertToEntity(depositDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getDepositById(@PathVariable Long id){
        return DepositTransformer.convertToDto(depositService.getDeposit(id));
    }

    @GetMapping("/balance/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalanceByUserId(@PathVariable Long userId){
        return depositService.getDeposit(userService.getUser(userId).getId()).getDepositBalance();
    }

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(){
        Deposit deposit = depositService.getDeposit();
        if (deposit != null) {
            return deposit.getDepositBalance();
        }
        return BigDecimal.ZERO;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getDeposit(){
        return DepositTransformer.convertToDto(depositService.getDeposit());
    }

}
