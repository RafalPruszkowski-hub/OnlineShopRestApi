package com.store.app.serviceImpl;

import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.mapper.UserMapper;
import com.store.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUser(String publicUserId) {
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new UserNotFoundException();

        UserDto returnValue = userMapper.toDto(userEntity);
        return returnValue;
    }


    @Override
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        List<UserDto> returnValue = userMapper.entityToDtoList(users);
        return returnValue;
    }
}
