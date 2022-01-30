package com.onbank.api.service;

import com.onbank.api.dto.CreateDepositDto;
import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;

public interface DepositService {
    void createDeposit(CreateDepositDto createDepositDto) throws Exception;
    Deposit getDeposit(Long id);
    Deposit getDepositByAccount(Account account);
    Deposit getDeposit();
}
