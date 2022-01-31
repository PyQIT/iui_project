package com.onbank.api.transformer;

import com.onbank.api.dto.CreateTransferDto;
import com.onbank.api.dto.TransferDetailsDto;
import com.onbank.api.dto.GetTransferDto;
import com.onbank.api.model.Transfer;
import org.springframework.beans.BeanUtils;

public class TransferTransformer {

    public static GetTransferDto convertToDto(Transfer transfer) {
        GetTransferDto getTransferDto = new GetTransferDto();
        BeanUtils.copyProperties(transfer, getTransferDto);
        return getTransferDto;
    }

    public static Transfer convertToEntity(GetTransferDto getTransferDto) {
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(getTransferDto, transfer);
        return transfer;
    }

    public static Transfer convertToEntity(CreateTransferDto transferDto) {
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferDto, transfer);
        return transfer;
    }

    public static TransferDetailsDto convertToTransferDetailsDto(Transfer transfer){
        TransferDetailsDto detailsDto = new TransferDetailsDto();
        BeanUtils.copyProperties(transfer, detailsDto);
        return detailsDto;
    }
}
