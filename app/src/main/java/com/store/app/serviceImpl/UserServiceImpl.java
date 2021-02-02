package com.store.app.serviceImpl;

import com.store.app.Util.UUIDGenerator;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.CreatingUserErrorException;
import com.store.app.exception.user.UserAlreadyExistException;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.mapper.UserMapper;
import com.store.app.security.UserPrincipal;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UUIDGenerator uuidGenerator;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new UserAlreadyExistException(userDto.getEmail());

        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPublicUserId(uuidGenerator.generate());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);
        if (storedUserDetails == null) throw new CreatingUserErrorException();

        cartService.create(storedUserDetails.getPublicUserId());//creating cart for user while creating new user

        UserDto returnValue = userMapper.toDto(storedUserDetails);
        return returnValue;
    }

    @Override
    public UserDto get(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        UserDto returnValue = userMapper.toDto(userEntity);
        return returnValue;
    }


    @Override
    public UserDto update(UserDto userDto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        UserEntity toSave = userMapper.applyChanges(userEntity, userDto);
        UserEntity updatedUser = userRepository.save(toSave);

        UserDto returnValue = userMapper.toDto(updatedUser);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new UserPrincipal(userEntity);
    }
}