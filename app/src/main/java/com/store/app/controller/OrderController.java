package com.store.app.controller;

import com.store.app.dto.CartItemDto;
import com.store.app.dto.OrderDto;
import com.store.app.dto.ProductDto;
import com.store.app.dto.UserDto;
import com.store.app.model.response.*;
import com.store.app.service.OrderService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
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

    private static OrderResponseModel changeOrderDtoToResponse(OrderDto orderDto) {
        OrderResponseModel returnValue = new OrderResponseModel();
        BeanUtils.copyProperties(orderDto, returnValue);


        //Hiding database Id and assigning correct proper objects to returnValue
        //USER
        UserResponseModel userResponseModel = new UserResponseModel();
        BeanUtils.copyProperties(orderDto.getUser(), userResponseModel);
        returnValue.setUser(userResponseModel);


        CartResponseModel cartResponseModel = new CartResponseModel();
        BeanUtils.copyProperties(orderDto.getCart(), cartResponseModel);

        List<CartItemResponseModel> returnItems = new ArrayList<>();
        for (CartItemDto cartItem : orderDto.getCart().getCartItems()) {
            CartItemResponseModel cartItemResponseModel = new CartItemResponseModel();
            BeanUtils.copyProperties(cartItem, cartItemResponseModel);

            //PRODUCT
            ProductResponseModel productResponseModel = new ProductResponseModel();
            BeanUtils.copyProperties(cartItem.getProduct(), productResponseModel);
            cartItemResponseModel.setProduct(productResponseModel);
            returnItems.add(cartItemResponseModel);
        }
        cartResponseModel.setCartItems(returnItems);
        returnValue.setCart(cartResponseModel);

        return returnValue;
    }

    //create new order and create new cart for user that will be used from this point to add/remove products
    @PostMapping
    @RequestMapping("/users/{id}/cart/order")
    public OrderResponseModel createOrder(@PathVariable("id") String publicUserId,
                                          Principal principal) {
        //Check if it's correct user
        UserDto userDto = userService.getUser(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) return null;

        //creating new order and new cart that will be from this point used by user who called that method
        OrderDto createdOrder = orderService.createOrder(publicUserId);

        OrderResponseModel returnValue = changeOrderDtoToResponse(createdOrder);
        return returnValue;
    }

    @GetMapping(path = "/{idUser}/orders/{idOrder}")
    public OrderResponseModel getOrder(@PathVariable(name = "idUser") String publicUserId,
                                       @PathVariable(name = "idOrder") String publicOrderId,
                                       Principal principal) {
        //Check if it's correct user
        UserDto userDto = userService.getUser(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) return null;

        OrderDto orderDto = orderService.getOrder(publicOrderId);

        OrderResponseModel returnValue = changeOrderDtoToResponse(orderDto);
        return returnValue;
    }

    @GetMapping(path = "/{idUser}/orders")
    public List<OrderResponseModel> getOrders(@PathVariable(name = "idUser") String publicUserId,
                                              Principal principal,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "25") int limit) {
        //Check if it's correct user
        UserDto userDto = userService.getUser(publicUserId);
        //TODO implement custom exception
        if (!principal.getName().equals(userDto.getEmail())) return null;


        List<OrderDto> orderDtoList = orderService.getOrders(publicUserId, page ,limit);

        List<OrderResponseModel> returnValue = new ArrayList();

        for (OrderDto orderDto : orderDtoList) {
            OrderResponseModel orderResponseModel = changeOrderDtoToResponse(orderDto);
            returnValue.add(orderResponseModel);
        }

        return returnValue;
    }
}
