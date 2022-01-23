package com.onbank.api.repository;

import com.onbank.api.model.Account;
import com.onbank.api.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Optional<Deposit> getDepositByAccount(Account account);
}
