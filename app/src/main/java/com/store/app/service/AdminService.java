package com.store.app.service;

import com.store.app.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    UserDto getUser(String publicUserId);

    List<UserDto> getUsers(int page, int limit);
}
