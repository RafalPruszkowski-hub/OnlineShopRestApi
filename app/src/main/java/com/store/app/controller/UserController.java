package com.store.app.controller;

import com.store.app.dto.UserDto;
import com.store.app.model.request.UserDetailsRequestModel;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel getUser(@PathVariable("id") String userId) {
        UserResponseModel returnValue = new UserResponseModel();

        UserDto userDto = userService.getUser(userId);

        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserResponseModel returnValue = new UserResponseModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @GetMapping
    public ArrayList<UserResponseModel> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ) {
        ArrayList<UserResponseModel> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto userDto : users) {
            UserResponseModel userResponseModel = new UserResponseModel();
            BeanUtils.copyProperties(userDto, userResponseModel);
            returnValue.add(userResponseModel);
        }
        return returnValue;
    }

    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel updateUser(@PathVariable("id") String id, @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserResponseModel returnValue = new UserResponseModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);

        UserDto createdUser = userService.updateUser(userDto, id);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    //TODO MAKE IT WORK
    /*@DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") String publicUserId) {
        userService.deleteUser(publicUserId);
    }*/
}