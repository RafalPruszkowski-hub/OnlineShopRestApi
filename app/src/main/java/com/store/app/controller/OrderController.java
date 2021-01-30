package com.store.app.controller;

import com.store.app.dto.OrderDto;
import com.store.app.model.response.OrderResponseModel;
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
    @RequestMapping("/order")
    public OrderResponseModel createOrder(Principal principal) {
        //Check if it's correct user
        String email = principal.getName();

        //creating new order and new cart that will be from this point used by user who called that method
        OrderDto createdOrder = orderService.create(email);

        OrderResponseModel returnValue = new OrderResponseModel(createdOrder);

        return returnValue;
    }

    @GetMapping(path = "/orders/{publicOrderId}")
    public OrderResponseModel getOrder(@PathVariable(name = "publicOrderId") String publicOrderId,
                                       Principal principal) {
        OrderDto orderDto = orderService.get(principal.getName(), publicOrderId);

        OrderResponseModel returnValue = new OrderResponseModel(orderDto);

        return returnValue;
    }

    @GetMapping(path = "/orders")
    public List<OrderResponseModel> getOrders(Principal principal,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<OrderDto> orderDtoList = orderService.getList(principal.getName(), page, limit);

        List<OrderResponseModel> returnValue = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            OrderResponseModel orderResponseModel = new OrderResponseModel(orderDto);
            returnValue.add(orderResponseModel);
        }

        return returnValue;
    }
}
