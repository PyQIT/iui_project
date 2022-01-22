package com.onbank.api.service;

import com.onbank.api.dto.UserRegistrationDto;
import com.onbank.api.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUser(Long id);

    User findByUsername(String username);

    User save(UserRegistrationDto registration);
}
