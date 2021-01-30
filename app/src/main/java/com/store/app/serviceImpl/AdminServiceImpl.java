package com.store.app.serviceImpl;

import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUser(String publicUserId) {
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new UserNotFoundException();

        UserDto returnValue = new UserDto(userEntity);
        return returnValue;
    }


    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = new UserDto(userEntity);
            returnValue.add(userDto);
        }

        return returnValue;
    }
}
