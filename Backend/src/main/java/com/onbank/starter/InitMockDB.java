package com.onbank.starter;

import com.onbank.api.model.Account;
import com.onbank.api.model.Transfer;
import com.onbank.api.model.User;
import com.onbank.api.model.enums.Nationality;
import com.onbank.api.model.enums.OperationType;
import com.onbank.api.model.enums.TransferState;
import com.onbank.api.repository.AccountRepository;
import com.onbank.api.repository.TransferRepository;
import com.onbank.api.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitMockDB {

    private static final String ACCOUNT_NAME_2 = "Mariusz Kowalski";
    @Getter
    private static final String ACCOUNT_NUMBER_2 = "PL13114000000787349936948743";

    private static final String ACCOUNT_NAME_1 = "Łukasz Nowak";
    @Getter
    private static final String ACCOUNT_NUMBER_1 = "PL48105000021021290496211203";

    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public InitMockDB(TransferRepository transferRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void init(){
        this.initTransfers();
        this.initAccounts();
        this.initUsers();
    }

    private void initAccounts(){
        List<Transfer> firstUserTransfers = new ArrayList<>();
        firstUserTransfers.add(transferRepository.getOne(1L));
        firstUserTransfers.add(transferRepository.getOne(3L));
        firstUserTransfers.add(transferRepository.getOne(4L));
        firstUserTransfers.add(transferRepository.getOne(5L));
        firstUserTransfers.add(transferRepository.getOne(6L));
        firstUserTransfers.add(transferRepository.getOne(7L));
        firstUserTransfers.add(transferRepository.getOne(8L));
        firstUserTransfers.add(transferRepository.getOne(9L));

        accountRepository.save(new Account(
                ACCOUNT_NUMBER_1,
                new BigDecimal("28500.00"),
                firstUserTransfers,
                1L
        ));

        List<Transfer> secondUserTransfers = new ArrayList<>();
        secondUserTransfers.add(transferRepository.getOne(2L));
        accountRepository.save(new Account(
                ACCOUNT_NUMBER_2,
                new BigDecimal("50300.20"),
                secondUserTransfers,
                2L
        ));
    }

    private void initUsers(){
        userRepository.save(new User(
                "Łukasz",
                "Nowak",
                "765966234",
                "lukasz.n@gmail.com",
                "user1",
                "$2a$12$UULg1Wju7WzYt2HuOPu9yOH5JAFVN4ORw9fhIU3IdMGebhRMsStR.", //"pass1" 12 rounds
                "91050500543",
                Nationality.PL,
                "Warszawa",
                LocalDate.of(1991, 5, 5),
                "Wasilewska",
                "AYC934855",
                accountRepository.getOne(1L)
        ));
        userRepository.save(new User(
                "Mariusz",
                "Kowalski",
                "475684665",
                "mariusz.k@gmail.com",
                "user2",
                "$2a$12$TVlHZXcS5mRc4pktrIesoeSMMMV1h7/yduzl/D4wXe0BB8v0jgrYy", //"pass2"
                "90010122002",
                Nationality.PL,
                "Kraków",
                LocalDate.of(1990, 1, 1),
                "Nowak",
                "AAB934438",
                accountRepository.getOne(2L)
        ));
    }

    private void initTransfers(){
        transferRepository.save(new Transfer(
                LocalDate.of(2019, 03, 23),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Przykładowy przelew z utf-8.",
                OperationType.NORMAL,
                new BigDecimal("150.0"),
                new BigDecimal("28350.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2019, 05, 11),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Przelew za zakupy.",
                OperationType.NORMAL,
                new BigDecimal("150.0"),
                new BigDecimal("28500.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2019, 07, 20),
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2,
                "Sprzedaż oprogramowania.",
                OperationType.NORMAL,
                new BigDecimal("1150.0"),
                new BigDecimal("53000.0"),
                TransferState.WAITING,
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2018, 12, 24),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Prezent gwiazdkowy.",
                OperationType.NORMAL,
                new BigDecimal("150.0"),
                new BigDecimal("20835.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2018, 2, 1),
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2,
                "Przelew na piwo.",
                OperationType.NORMAL,
                new BigDecimal("15.0"),
                new BigDecimal("20985.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2018, 6, 25),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Prezent urodzinowy.",
                OperationType.NORMAL,
                new BigDecimal("15.0"),
                new BigDecimal("21000.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2018, 11, 10),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Kredyt na ogrzewanie",
                OperationType.NORMAL,
                new BigDecimal("1500.0"),
                new BigDecimal("23500.0"),
                TransferState.REALIZED,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2019, 2, 28),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Przelew bez okazji.",
                OperationType.NORMAL,
                new BigDecimal("150.0"),
                new BigDecimal("24150.0"),
                TransferState.WAITING,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));

        transferRepository.save(new Transfer(
                LocalDate.of(2019, 7, 29),
                ACCOUNT_NAME_1,
                ACCOUNT_NUMBER_1,
                "Pieniądze dla studentów",
                OperationType.NORMAL,
                new BigDecimal("1.0"),
                new BigDecimal("25000.2"),
                TransferState.WAITING,
                ACCOUNT_NAME_2,
                ACCOUNT_NUMBER_2
        ));
    }
}
