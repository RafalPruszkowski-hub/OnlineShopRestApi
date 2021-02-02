package com.store.app.controller;

import com.store.app.dto.UserDto;
import com.store.app.mapper.UserMapper;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(path = "/users/{publicUserId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel get(@PathVariable(name = "publicUserId") String publicUserId) {
        UserDto userDto = adminService.getUser(publicUserId);

        UserResponseModel returnValue = userMapper.toResponse(userDto);
        return returnValue;
    }

    @GetMapping(path = "/users")
    public List<UserResponseModel> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ) {
        List<UserDto> users = adminService.getUsers(page, limit);

        List<UserResponseModel> returnValue = userMapper.dtoToResponseList(users);
        return returnValue;
    }
}
