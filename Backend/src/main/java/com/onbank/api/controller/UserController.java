package com.onbank.api.controller;

import com.onbank.api.dto.UserDto;
import com.onbank.api.service.UserService;
import com.onbank.api.transformer.UserTransformer;
import com.onbank.http.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/getProfileUser")
public class UserController {

    private final UserService userService;
    private final AuthUser authUser;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long id){
        return UserTransformer.convertToDto(userService.getUser(id));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser() {
        return UserTransformer.convertToDto(authUser.getUser());
    }
}
