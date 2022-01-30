package com.onbank.api.controller;

import com.onbank.api.dto.UserDetailedDto;
import com.onbank.api.dto.UserDto;
import com.onbank.api.service.UserDetailedService;
import com.onbank.api.service.UserService;
import com.onbank.api.transformer.UserTransformer;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/getProfileUser")
public class UserController {

    private final UserService userService;
    private final UserDetailedService userDetailedService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long id){
        return UserTransformer.convertToDto(userService.getUser(id));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDetailedDto getUser() {
        return userDetailedService.getUserDetails();
    }
}
