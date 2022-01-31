package com.onbank.api.transformer;

import com.onbank.api.dto.GetTransferDto;
import com.onbank.api.model.Transfer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferTransformerTest {

    @Test
    void convertToDtoSuccessTest(){
        Transfer transfer = new Transfer();
        transfer.setRecipientName("Abacki");
        TransferTransformer transferTransformer = new TransferTransformer();

        assertEquals(true, transfer.getRecipientName().equals(transferTransformer.convertToDto(transfer).getRecipientName()));
    }

    @Test
    void convertToEntitySuccessTest(){
        GetTransferDto getTransferDto = new GetTransferDto();
        getTransferDto.setRecipientName("Abacki");
        TransferTransformer transferTransformer = new TransferTransformer();

        assertEquals(true, getTransferDto.getRecipientName().equals(transferTransformer.convertToEntity(getTransferDto).getRecipientName()));
    }

}
