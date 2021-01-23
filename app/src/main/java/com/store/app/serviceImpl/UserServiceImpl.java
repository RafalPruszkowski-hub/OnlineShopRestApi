package com.store.app.serviceImpl;

import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.UserAlreadyExistException;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.security.UserPrincipal;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserDto user) {
        //TODO Create regex for not allowing misspelled inputs
        if (userRepository.findByEmail(user.getEmail()) != null) throw new UserAlreadyExistException(user.getEmail());

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        String publicUserId = UUID.randomUUID().toString();
        userEntity.setPublicUserId(publicUserId);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);
        cartService.create(storedUserDetails.getPublicUserId());//creating cart for user while creating new user


        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto get(String publicUserId) {
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new UserNotFoundException();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }


    @Override
    public UserDto update(UserDto userDto, String publicUserId) {
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new UserNotFoundException();


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
    public List<UserDto> getList(int page, int limit) {
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new UserPrincipal(userEntity);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }
}
