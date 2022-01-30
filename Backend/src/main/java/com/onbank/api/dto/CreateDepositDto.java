package com.onbank.api.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class CreateDepositDto {

    @DecimalMin("1.00")
    @DecimalMax("10000.00")
    @Digits(integer = 5, fraction = 2)
    BigDecimal depositAmount;

    @DecimalMin("1.00")
    @Digits(integer = 5, fraction = 2)
    BigDecimal expectedReturn;

}
