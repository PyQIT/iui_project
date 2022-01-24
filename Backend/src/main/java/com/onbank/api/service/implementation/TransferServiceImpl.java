package com.onbank.api.service.implementation;

import com.onbank.api.model.Transfer;
import com.onbank.api.model.enums.TransferState;
import com.onbank.api.repository.TransferRepository;
import com.onbank.api.service.TransferService;
import com.onbank.exceptions.TransferNotFoundException;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final AuthUser authUser;

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> sent = transferRepository.getTransferByRealizationStateInAndSenderAccountNumber(
                Collections.singletonList(TransferState.REALIZED),
                authUser.getUser().getAccount().getNumber()
        );
        List<Transfer> received = transferRepository.getTransferByRealizationStateInAndRecipientAccountNumber(
                Collections.singletonList(TransferState.REALIZED),
                authUser.getUser().getAccount().getNumber()
        );
        sent.addAll(received);
        return sent;
    }

    @Override
    public List<Transfer> getLockedTransactions() {
        return transferRepository.getTransferByRealizationStateInAndSenderAccountNumber(
                List.of(TransferState.WAITING, TransferState.IN_PROGRESS),
                authUser.getUser().getAccount().getNumber()
        );
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        transfer.setSenderName(authUser.getUser().getName());
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer getTransfer(Long id) {
        return transferRepository.findByIdAndSenderAccountNumber(
                id,
                authUser.getUser().getAccount().getNumber()
        ).orElseThrow(() -> new TransferNotFoundException("Transfer id=" + id + " not found.")
        );
    }

    @Override
    public BigDecimal getLocksAmount() {
        List<Transfer> transfers = transferRepository.findByRealizationStateAndDateBeforeAndSenderAccountNumber(
                TransferState.WAITING, LocalDate.now(),
                authUser.getUser().getAccount().getNumber()
        );
        return transfers.stream()
                .map(Transfer::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
