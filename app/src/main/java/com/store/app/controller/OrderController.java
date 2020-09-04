package com.store.app.controller;

import com.store.app.database.entity.CartItemEntity;
import com.store.app.dto.CartItemDto;
import com.store.app.dto.OrderDto;
import com.store.app.dto.ProductDto;
import com.store.app.model.response.*;
import com.store.app.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    //create new order and create new cart for user that will be used from this point to add/remove products
    @PostMapping
    @RequestMapping("/users/{id}/cart/order")
    public OrderResponseModel createOrder(@PathVariable("id") String publicUserId) {
        OrderResponseModel returnValue = new OrderResponseModel();

        //creating new order and new cart that will be from this point used by user who called that method
        OrderDto createdOrder = orderService.createOrder(publicUserId);
        BeanUtils.copyProperties(createdOrder, returnValue);

        //Hiding database Id and assigning correct proper objects to returnValue
        //USER
        UserResponseModel userResponseModel = new UserResponseModel();
        BeanUtils.copyProperties(createdOrder.getUser(),userResponseModel);
        returnValue.setUser(userResponseModel);


        //CART
        CartResponseModel cartResponseModel = new CartResponseModel();
        BeanUtils.copyProperties(createdOrder.getCart(),cartResponseModel);

        List<CartItemResponseModel> returnItems = new ArrayList<>();
        for(CartItemDto cartItem : createdOrder.getCart().getCartItems()){
            CartItemResponseModel cartItemResponseModel = new CartItemResponseModel();
            BeanUtils.copyProperties(cartItem,cartItemResponseModel);

            //PRODUCT
            ProductResponseModel productResponseModel = new ProductResponseModel();
            BeanUtils.copyProperties(cartItem.getProduct(),productResponseModel);
            cartItemResponseModel.setProduct(productResponseModel);
            returnItems.add(cartItemResponseModel);
        }

        cartResponseModel.setCartItems(returnItems);
        returnValue.setCart(cartResponseModel);

        return returnValue;
    }
}
