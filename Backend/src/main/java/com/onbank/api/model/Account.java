package com.onbank.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 5, max = 60)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "accountbalance", nullable = false)
    private BigDecimal accountBalance;

    @OneToMany
    private List<Transfer> transfers;

    @Column(name = "userId", nullable = false)
    private Long userId;
}
