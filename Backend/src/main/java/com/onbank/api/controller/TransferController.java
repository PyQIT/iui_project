package com.onbank.api.controller;

import com.onbank.api.dto.CreateTransferDto;
import com.onbank.api.dto.TransferDetailsDto;
import com.onbank.api.dto.GetTransferDto;
import com.onbank.api.model.Transfer;
import com.onbank.api.service.TransferService;
import com.onbank.api.transformer.TransferTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
@Validated
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetTransferDto> getTransfers() {
        return transferService.getTransfers();
    }

    @GetMapping("/getLockedTransactions")
    @ResponseStatus(HttpStatus.OK)
    public List<GetTransferDto> getLockedTransactions() {
        List<Transfer> transfer = transferService.getLockedTransactions();
        return transfer.stream().map(TransferTransformer::convertToDto).collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createTransfer(@RequestBody CreateTransferDto createTransferDto) throws Exception {
        transferService.createTransfer(createTransferDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferDetailsDto getTransfer(@PathVariable Long id){
        return TransferTransformer.convertToTransferDetailsDto(transferService.getTransfer(id));
    }

    @GetMapping("/getLocksAmount")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getLocksAmount() {
        return transferService.getLocksAmount();
    }
}

