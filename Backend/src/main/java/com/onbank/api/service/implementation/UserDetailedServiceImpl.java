package com.onbank.api.service.implementation;

import com.onbank.api.dto.UserDetailedDto;
import com.onbank.api.model.User;
import com.onbank.api.service.TransferService;
import com.onbank.api.service.UserDetailedService;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailedServiceImpl implements UserDetailedService {

    private final AuthUser authUser;
    private final TransferService transferService;

    @Override
    public UserDetailedDto getUserDetails() {
        User user = authUser.getUser();
        return new UserDetailedDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAccount().getNumber(),
                user.getAccount().getAccountBalance(),
                transferService.getLocksAmount()
        );

    }
}
