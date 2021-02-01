package com.store.app.controller;

import com.store.app.dto.UserDto;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping(path = "/users/{publicUserId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel get(@PathVariable(name = "publicUserId") String publicUserId) {
        UserDto userDto = adminService.getUser(publicUserId);

        UserResponseModel returnValue = new UserResponseModel(userDto);

        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @GetMapping(path = "/users")
    public ArrayList<UserResponseModel> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ) {
        List<UserDto> users = adminService.getUsers(page, limit);

        ArrayList<UserResponseModel> returnValue = new ArrayList<>();
        for (UserDto userDto : users) {
            UserResponseModel userResponseModel = new UserResponseModel(userDto);
            returnValue.add(userResponseModel);
        }
        return returnValue;
    }
}
