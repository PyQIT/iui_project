package com.onbank.api.transformer;

import com.onbank.api.dto.DepositDto;
import com.onbank.api.model.Deposit;
import org.springframework.beans.BeanUtils;

public class DepositTransformer {
    public static Deposit convertToEntity(DepositDto dto) {
        Deposit deposit = new Deposit();
        BeanUtils.copyProperties(dto, deposit);
        return deposit;
    }

    public static DepositDto convertToDto(Deposit deposit) {
        DepositDto dto = new DepositDto();
        BeanUtils.copyProperties(deposit, dto);
        return dto;
    }
}
