package com.onbank.api.service.implementation;

import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import com.onbank.api.repository.AccountRepository;
import com.onbank.api.repository.DepositRepository;
import com.onbank.api.service.DepositService;
import com.onbank.exceptions.AccountNotFoundException;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;
    private final AuthUser authUser;

    @Override
    public Deposit createDeposit(Deposit deposit){
        Account account = deposit.getAccount();
        account.setAccountBalance(account.getAccountBalance().subtract(deposit.getDepositBalance()));
        accountRepository.save(account);

        deposit.setDepositInterest(new BigDecimal("1.03"));
        deposit.setReturnBalance(deposit.getDepositBalance().multiply(deposit.getDepositInterest()));

        return depositRepository.save(deposit);
    }

    @Override
    public Deposit getDeposit(Long id){
        return depositRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException("Deposit id=" + id + " not found.")
        );
    }

    @Override
    public Deposit getDepositByAccount(Account account){
        return depositRepository.getDepositByAccount(account).orElse(null);
    }

    @Override
    public Deposit getDeposit() {
        return depositRepository.getDepositByAccount(authUser.getUser().getAccount()).orElse(null);
    }
}
