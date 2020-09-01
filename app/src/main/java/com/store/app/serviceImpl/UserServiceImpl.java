package com.store.app.serviceImpl;

import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Override
    public UserDto createUser(UserDto user) {
        //TODO Create regex for not allowing misspelled inputs
        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record Already Exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        String publicUserId = UUID.randomUUID().toString();
        userEntity.setPublicUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);
        cartService.createCart(storedUserDetails.getUserId());//creating cart for user while creating new user


        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String publicUserId) {
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new RuntimeException("Record not Found");
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String publicUserId) {
        //TODO Create regex for not allowing misspelled inputs
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new RuntimeException("No record found with this id " + publicUserId);


        if (userDto.getFirstName() != null) userEntity.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null) userEntity.setLastName(userDto.getLastName());
        if (userDto.getAddress() != null) userEntity.setAddress(userDto.getAddress());
        if (userDto.getCity() != null) userEntity.setCity(userDto.getCity());
        if (userDto.getTelephone() != null) userEntity.setTelephone(userDto.getTelephone());
        if (userDto.getEmail() != null) userEntity.setEmail(userDto.getEmail());

        UserEntity updatedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUser(String publicUserId) {
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new RuntimeException("No record found with this id " + publicUserId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }
}
