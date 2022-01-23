package com.onbank.api.service.implementation;

import com.onbank.api.model.Transfer;
import com.onbank.api.model.enums.TransferState;
import com.onbank.api.repository.TransferRepository;
import com.onbank.api.service.TransferService;
import com.onbank.exceptions.TransferNotFoundException;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final AuthUser authUser;

    @Override
    public List<Transfer> getTransfers() {
        return transferRepository.getTransferByRealizationStateAndSenderAccountNumber(
                TransferState.REALIZED,
                authUser.getUser().getAccount().getNumber()
        );
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
}
