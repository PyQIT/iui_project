package com.onbank.api.dto;

import com.onbank.api.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;

    @NotNull(message = "Number cannot be null.")
    private String number;

    @NotNull(message = "Account balance cannot be null.")
    private BigDecimal accountBalance;

    @NotNull(message = "Account owner cannot be null.")
    private Long userId;
}
