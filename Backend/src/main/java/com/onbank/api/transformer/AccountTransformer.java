package com.onbank.api.transformer;

import com.onbank.api.dto.AccountDto;
import com.onbank.api.model.Account;

import org.springframework.beans.BeanUtils;

public class AccountTransformer {
    public static Account convertToEntity(AccountDto dto) {
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        return account;
    }

    public static AccountDto convertToDto(Account user) {
        AccountDto dto = new AccountDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
