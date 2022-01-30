package com.onbank.api.repository;

import com.onbank.api.model.Transfer;
import com.onbank.api.model.enums.TransferState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByRealizationStateAndDateBefore(TransferState realizationState, LocalDate firstDate);

    List<Transfer> findByRealizationStateAndSenderAccountNumber(TransferState realizationState, String senderAccountNumber);
    List<Transfer> getTransferByRealizationStateInAndSenderAccountNumber(List<TransferState> transferStates, String senderAccountNumber);
    //Optional<Transfer> findByIdAndSenderAccountNumber(Long id, String senderAccountNumber);
    Optional<Transfer> findByIdAndSenderAccountNumberOrIdAndRecipientAccountNumber(Long id, String senderAccountNumber,Long id2, String recipientAccountNumber);
    List<Transfer> getTransferByRealizationStateInAndRecipientAccountNumber(List<TransferState> transferStates, String recipientAccountNumber);

    List<Transfer> findByRealizationState(TransferState waiting);
}
