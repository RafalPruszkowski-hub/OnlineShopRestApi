package com.store.app.service;

import com.store.app.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDto create(UserDto user);

    UserDto get(String email);

    UserDto update(UserDto req, String email);

}
