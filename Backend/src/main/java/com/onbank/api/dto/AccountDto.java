package com.onbank.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private String number;
    private String password;
    private BigDecimal accountBalance;
    private Long UserId;
}
