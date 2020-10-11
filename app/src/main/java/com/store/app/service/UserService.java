package com.store.app.service;

import com.store.app.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);

    UserDto getUser(String id);

    UserDto updateUser(UserDto req, String id);

    List<UserDto> getUsers(int page, int limit);

    UserDto getUserByEmail(String email);
}
