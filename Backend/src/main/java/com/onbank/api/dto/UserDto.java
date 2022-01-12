package com.onbank.api.dto;

import com.onbank.api.model.Account;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private Account account;
}
