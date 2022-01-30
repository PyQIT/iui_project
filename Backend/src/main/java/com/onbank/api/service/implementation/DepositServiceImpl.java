package com.onbank.api.service.implementation;

import com.onbank.api.dto.CreateDepositDto;
import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import com.onbank.api.model.User;
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
    public void createDeposit(CreateDepositDto createDepositDto) throws Exception {
        User user = authUser.getUser();
        Account account = user.getAccount();
        if (depositRepository.getDepositByAccount(account).orElse(null) != null) {
            throw new Exception("Nie można stworzyć kolejnej lokaty");
        }
        BigDecimal depositAmount = createDepositDto.getDepositAmount();
        BigDecimal expectedReturn = createDepositDto.getExpectedReturn();

        Deposit deposit = new Deposit();
        deposit.setAccount(account);
        deposit.setReturnBalance(depositAmount.multiply(deposit.getDepositInterest()));
        if (deposit.getReturnBalance().compareTo(expectedReturn) != 0) {
            System.out.println(deposit.getReturnBalance() + " != " + expectedReturn);
            System.out.println(deposit.getReturnBalance().compareTo(expectedReturn));
            throw new Exception("Niepoprawna kwota zwrotu");
        }
        deposit.setDepositBalance(depositAmount);
        if (deposit.getDepositBalance().compareTo(account.getAccountBalance()) > 0) {
            throw new Exception("Niewystarczające środki");
        }

        account.setAccountBalance(account.getAccountBalance().subtract(deposit.getDepositBalance()));
        accountRepository.save(account);

        depositRepository.save(deposit);
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
