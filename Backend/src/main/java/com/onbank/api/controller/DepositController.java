package com.onbank.api.controller;

import com.onbank.api.dto.DepositDto;
import com.onbank.api.model.Account;
import com.onbank.api.service.DepositService;
import com.onbank.api.transformer.DepositTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deposits")
@Validated
public class DepositController {
    private final DepositService depositService;

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getDepositByAccount(@RequestBody Account account) {
        return DepositTransformer.convertToDto(depositService.getDepositByAccount(account));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createDeposit(@Valid @RequestBody DepositDto depositDto) {

        depositService.createDeposit(DepositTransformer.convertToEntity(depositDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getDepositById(@PathVariable Long id){
        return DepositTransformer.convertToDto(depositService.getDeposit(id));
    }

}
