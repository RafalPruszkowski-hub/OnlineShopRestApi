package com.store.app.serviceImpl;

import com.store.app.database.entity.*;
import com.store.app.database.repository.OrderRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import com.store.app.dto.OrderDto;
import com.store.app.dto.UserDto;
import com.store.app.model.response.OrderResponseModel;
import com.store.app.service.CartService;
import com.store.app.service.OrderService;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Override
    public OrderDto createOrder(String publicUserId) {
        OrderDto returnValue = new OrderDto();
        OrderEntity orderEntity = new OrderEntity();

        //Set user
        UserEntity userEntity = new UserEntity();
        UserDto userDto = userService.getUser(publicUserId);
        BeanUtils.copyProperties(userDto,userEntity);
        orderEntity.setUser(userEntity);

        //Set cart
        CartEntity cartEntity = new CartEntity();
        CartDto cartDto = cartService.getCartCurrentOnPublicUserId(publicUserId);
        BeanUtils.copyProperties(cartDto,cartEntity);
        orderEntity.setCart(cartEntity);

        //Set publicOrderId

        orderEntity.setPublicOrderId(UUID.randomUUID().toString());

        //Check if cart does not have more item then in stock
        for(CartItemDto cartItemTmp : cartDto.getCartItems()){
            int stock = cartItemTmp.getProduct().getQuantityOfStock();
            if(stock<=cartItemTmp.getQuantity())
                throw new RuntimeException("Sorry but you have put more products to cart then there is in stock "+cartItemTmp.getProduct().getPublicProductId());
        }

        //Check if cart contains any car items

        if(cartDto.getCartItems().isEmpty())
            throw new RuntimeException("Your cart is empty please put some item into it before making a order");

        //Save Order

        OrderEntity savedEntity = orderRepository.save(orderEntity);

        //Update Products stock

        for(CartItemDto cartItemTmp : cartDto.getCartItems()){
            productService.updateProductStock(cartItemTmp.getQuantity(),cartItemTmp.getProduct().getPublicProductId());
        }

        //SetOrderForCart

        cartService.saveCartOrder(savedEntity.getCart().getPublicCartId(), savedEntity.getPublicOrderId());


        //Assigning nested objects value to
        BeanUtils.copyProperties(orderEntity,returnValue);

        returnValue.setUser(userService.getUser(publicUserId));
        returnValue.setCart(cartService.getCartCurrentOnPublicUserId(publicUserId));

        //Create New Cart

        cartService.createCart(publicUserId);
        return returnValue;
    }
}
