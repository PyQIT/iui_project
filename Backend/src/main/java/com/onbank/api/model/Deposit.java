package com.onbank.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "deposit")
@EqualsAndHashCode(callSuper = false)
public class Deposit  extends EntityCore  {

    @Column(name = "depositBalance", nullable = false)
    private BigDecimal depositBalance;

    @Column(name = "returnBalance", nullable = false)
    private BigDecimal returnBalance;

    @Column(name = "depositInterest", nullable = false)
    private BigDecimal depositInterest;

    @OneToOne
    @JoinColumn(name = "account")
    private Account account;

    @NotNull(message = "Date cannot be empty.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public Deposit() {
        endDate = LocalDate.now().plusYears(1);
        depositInterest = new BigDecimal("1.06");
        active = true;
    }
}
