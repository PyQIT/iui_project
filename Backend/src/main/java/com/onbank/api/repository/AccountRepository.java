package com.onbank.api.repository;

import com.onbank.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account getAccountsByNumber(String number);
    Account getAccountByUserId(Long Id);
    List<Account> getAccountsByIds(List<Account> accounts);
}
