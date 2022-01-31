package com.onbank.api.service;

import com.onbank.api.dto.CreateTransferDto;
import com.onbank.api.dto.GetTransferDto;
import com.onbank.api.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

    List<GetTransferDto> getTransfers();
    List<Transfer> getLockedTransactions();
    Transfer createTransfer(CreateTransferDto createTransferDto) throws Exception;
    Transfer getTransfer(Long id);
    BigDecimal getLocksAmount();
}
