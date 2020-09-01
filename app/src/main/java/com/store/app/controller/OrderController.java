package com.store.app.controller;

import com.store.app.dto.OrderDto;
import com.store.app.model.response.OrderOnUserResponseModel;
import com.store.app.model.response.OrderResponseModel;
import com.store.app.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    //create new order and create new cart for user that will be used from this point to add/remove products
    @PostMapping
    @RequestMapping("/users/{id}/cart/order")
    public OrderOnUserResponseModel createOrder(@PathVariable("id") String publicUserId) {
        OrderOnUserResponseModel returnValue = new OrderOnUserResponseModel();

        OrderDto createdOrder = orderService.createOrder(publicUserId);
        BeanUtils.copyProperties(createdOrder, returnValue);

        returnValue.setCart(createdOrder.getCart());

        return returnValue;
    }

    //get list of all orders for user on publicUserId
    @GetMapping
    @RequestMapping("/users/{id}/orders")
    public List<OrderResponseModel> getOrders(@PathVariable("id") String publicUserId) {
        List<OrderResponseModel> returnValue = new ArrayList<>();

        List<OrderDto> ordersDtoList = orderService.getOrdersForUser(publicUserId);


        for (OrderDto orderDto : ordersDtoList) {
            OrderResponseModel orderResponseModel = new OrderResponseModel();
            BeanUtils.copyProperties(orderDto, orderResponseModel);

            orderResponseModel.setCart(orderDto.getCart());
            orderResponseModel.setUser(orderDto.getUser());

            returnValue.add(orderResponseModel);
        }

        return returnValue;
    }
}
