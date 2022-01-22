package com.onbank.api.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@EqualsAndHashCode(callSuper = false)
public class Account extends EntityCore {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "accountbalance", nullable = false)
    private BigDecimal accountBalance;

    @OneToMany
    private List<Transfer> transfers;

    @Column(name = "userId", nullable = false)
    private Long userId;
}
