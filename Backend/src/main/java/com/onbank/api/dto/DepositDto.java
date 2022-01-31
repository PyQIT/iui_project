package com.onbank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onbank.api.model.Account;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DepositDto {
    private Long id;
    private BigDecimal depositBalance;
    private BigDecimal returnBalance;
    private BigDecimal depositInterest;
    @NotNull(message = "Date cannot be empty.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    @NotNull(message = "Date cannot be empty.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Boolean active;
}
