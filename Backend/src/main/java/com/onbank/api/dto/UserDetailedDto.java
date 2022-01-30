package com.onbank.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserDetailedDto {
    private Long id;
    private String name;
    private String surname;
    private String accountNumber;
    private BigDecimal accountBalance;
    private BigDecimal locksAmount;
}
