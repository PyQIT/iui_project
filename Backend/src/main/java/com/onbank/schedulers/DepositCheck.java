package com.onbank.schedulers;

import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import com.onbank.api.repository.AccountRepository;
import com.onbank.api.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DepositCheck {
    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;

    @Scheduled(cron = "${onbank.deposit.check.date}")
    public void checkDepositStatus(){
        List<Deposit> deposits = depositRepository.findAll();
        List<Deposit> closedDeposits = new ArrayList<Deposit>();

        depositRepository.saveAll(deposits.stream()
                .filter(deposit -> deposit.getActive().equals(true))
                .filter(deposit -> deposit.getEndDate().equals(LocalDate.now()))
                .peek(deposit -> deposit.setActive(false))
                .collect(Collectors.toCollection(() -> closedDeposits)));

        List<Account> accounts = closedDeposits.stream()
                                .map(Deposit::getAccount)
                                .collect(Collectors.toList());

        for (Account a: accounts) {
            for (Deposit d: closedDeposits){
                if(d.getAccount().equals(a)){
                    a.setAccountBalance(a.getAccountBalance().add(d.getReturnBalance()));
                    break;
                }
            }
        }

        accountRepository.saveAll(accounts);
    }
}
