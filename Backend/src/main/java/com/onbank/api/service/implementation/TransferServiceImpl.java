package com.onbank.api.service.implementation;

import com.onbank.api.dto.CreateTransferDto;
import com.onbank.api.dto.GetTransferDto;
import com.onbank.api.model.Account;
import com.onbank.api.model.Transfer;
import com.onbank.api.model.User;
import com.onbank.api.model.enums.TransferState;
import com.onbank.api.repository.TransferRepository;
import com.onbank.api.service.TransferService;
import com.onbank.api.transformer.TransferTransformer;
import com.onbank.exceptions.TransferNotFoundException;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final AuthUser authUser;

    @Override
    public List<GetTransferDto> getTransfers() {
        Account account = authUser.getUser().getAccount();
        List<Transfer> sent = transferRepository.getTransferByRealizationStateInAndSenderAccountNumber(
                Collections.singletonList(TransferState.REALIZED),
                authUser.getUser().getAccount().getNumber()
        );
        List<Transfer> received = transferRepository.getTransferByRealizationStateInAndRecipientAccountNumber(
                Collections.singletonList(TransferState.REALIZED),
                authUser.getUser().getAccount().getNumber()
        );
        sent.addAll(received);
        List<GetTransferDto> getTransfersDto = sent.stream().map(TransferTransformer::convertToDto).collect(Collectors.toList());
/*        getTransfersDto.forEach(f -> {
            f.setAccountBalance((
                    account.getNumber().equals(f.getSenderAccountNumber())
                            ? f. : ))
        });*/
        return getTransfersDto;
    }

    @Override
    public List<Transfer> getLockedTransactions() {
        return transferRepository.getTransferByRealizationStateInAndSenderAccountNumber(
                List.of(TransferState.WAITING, TransferState.IN_PROGRESS),
                authUser.getUser().getAccount().getNumber()
        );
    }

    @Override
    public Transfer createTransfer(CreateTransferDto createTransferDto) throws Exception {
        User user = authUser.getUser();
        Account account = user.getAccount();

        Transfer transfer = new Transfer();
        transfer.setSenderName(user.getName());
        transfer.setSenderAccountNumber(account.getNumber());
        if (account.getAccountBalance().compareTo(createTransferDto.getAmount()) < 0) {
            throw new Exception("Niewystarczające środki");
        }
        transfer.setSenderAccountBalance(account.getAccountBalance().subtract(createTransferDto.getAmount()));

        transfer.setDate(createTransferDto.getDate());
        transfer.setRecipientName(createTransferDto.getRecipientName());
        transfer.setRecipientAccountNumber(createTransferDto.getRecipientAccountNumber());
        transfer.setDescription(createTransferDto.getDescription());
        transfer.setAmount(createTransferDto.getAmount());
        transfer.setOperationType(createTransferDto.getOperationType());

        if (transfer.getRecipientAccountNumber().equals(transfer.getSenderAccountNumber())) {
            throw new Exception("Nie możesz wysłać pieniędzy sam do siebie");
        }

        account.setAccountBalance(account.getAccountBalance().subtract(createTransferDto.getAmount()));
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer getTransfer(Long id) {
        return transferRepository.findByIdAndSenderAccountNumberOrIdAndRecipientAccountNumber(
                id,
                authUser.getUser().getAccount().getNumber(),
                id,
                authUser.getUser().getAccount().getNumber()
        ).orElseThrow(() -> new TransferNotFoundException("Transfer id=" + id + " not found.")
        );
    }

    @Override
    public BigDecimal getLocksAmount() {
        List<Transfer> transfers = transferRepository.findByRealizationStateAndSenderAccountNumber(
                TransferState.WAITING,
                authUser.getUser().getAccount().getNumber()
        );
        return transfers.stream()
                .map(Transfer::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
