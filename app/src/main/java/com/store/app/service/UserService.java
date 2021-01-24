package com.store.app.service;

import com.store.app.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDto create(UserDto user);

    UserDto get(String email);

    UserDto update(UserDto req, String email);

    List<UserDto> getList(int page, int limit);

    UserDto getUserByEmail(String email);
}
