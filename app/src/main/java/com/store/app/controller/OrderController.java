package com.store.app.controller;

import com.store.app.dto.OrderDto;
import com.store.app.dto.UserDto;
import com.store.app.model.response.*;
import com.store.app.service.OrderService;
import com.store.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    //create new order and create new cart for user that will be used from this point to add/remove products
    @PostMapping
    @RequestMapping("/users/{id}/cart/order")
    public OrderResponseModel createOrder(@PathVariable("id") String publicUserId,
                                          Principal principal) {
        //Check if it's correct user
        UserDto userDto = userService.get(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) throw new RuntimeException("Wrong authorization header is provided");

        //creating new order and new cart that will be from this point used by user who called that method
        OrderDto createdOrder = orderService.create(publicUserId);

        OrderResponseModel returnValue = new OrderResponseModel(createdOrder);

        return returnValue;
    }

    @GetMapping(path = "/users/{idUser}/orders/{idOrder}")
    public OrderResponseModel getOrder(@PathVariable(name = "idUser") String publicUserId,
                                       @PathVariable(name = "idOrder") String publicOrderId,
                                       Principal principal) {
        //Check if it's correct user
        UserDto userDto = userService.get(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) throw new RuntimeException("Wrong authorization header is provided");

        OrderDto orderDto = orderService.get(publicOrderId);

        OrderResponseModel returnValue = new OrderResponseModel(orderDto);

        return returnValue;
    }

    @GetMapping(path = "/users/{idUser}/orders")
    public List<OrderResponseModel> getOrders(@PathVariable(name = "idUser") String publicUserId,
                                              Principal principal,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "25") int limit) {
        //Check if it's correct user
        UserDto userDto = userService.get(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) throw new RuntimeException("Wrong authorization header is provided");


        List<OrderDto> orderDtoList = orderService.getList(publicUserId, page ,limit);

        List<OrderResponseModel> returnValue = new ArrayList<>();
        for(OrderDto orderDto : orderDtoList){
            OrderResponseModel orderResponseModel = new OrderResponseModel(orderDto);
            returnValue.add(orderResponseModel);
        }

        return returnValue;
    }
}
