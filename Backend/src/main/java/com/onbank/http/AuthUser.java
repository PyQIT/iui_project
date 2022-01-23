package com.onbank.http;

import com.onbank.api.model.User;
import com.onbank.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUser {

    private final UserService userService;

    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
/*        System.out.println(auth.getPrincipal());
        System.out.println(auth.getName());
        System.out.println(auth.getDetails());
        System.out.println(auth.getCredentials());*/
        return userService.findByUsername(auth.getName());
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
