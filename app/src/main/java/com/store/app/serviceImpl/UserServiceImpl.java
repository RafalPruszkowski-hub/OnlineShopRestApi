package com.store.app.serviceImpl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UUIDGenerator uuidGenerator;

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) throw new UserAlreadyExistException(userDto.getEmail());

        UserEntity userEntity = new UserEntity(userDto);
        userEntity.setPublicUserId(uuidGenerator.generate());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);
        if(storedUserDetails==null) throw new CreatingUserErrorException();

        cartService.create(storedUserDetails.getPublicUserId());//creating cart for user while creating new user

        UserDto returnValue = new UserDto(storedUserDetails);

        return returnValue;
    }

    @Override
    public UserDto get(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        UserDto returnValue = new UserDto(userEntity);
        return returnValue;
    }


    @Override
    public UserDto update(UserDto userDto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        applyChanges(userDto,userEntity);

        UserEntity updatedUser = userRepository.save(userEntity);
        UserDto returnValue = new UserDto(updatedUser);

        return returnValue;
    }

    private void applyChanges(UserDto userDto, UserEntity userEntity) {
        if (userDto.getFirstName() != null) userEntity.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null) userEntity.setLastName(userDto.getLastName());
        if (userDto.getAddress() != null) userEntity.setAddress(userDto.getAddress());
        if (userDto.getCity() != null) userEntity.setCity(userDto.getCity());
        if (userDto.getTelephone() != null) userEntity.setTelephone(userDto.getTelephone());
        if (userDto.getEmail() != null) userEntity.setEmail(userDto.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new UserPrincipal(userEntity);
    }
}
