package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.OrderRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.OrderDto;
import com.store.app.service.CartService;
import com.store.app.service.OrderService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    UserRepository userRepository;

    @Override
    public OrderDto createOrder(String publicUserId) {
        //TODO do not allow to use this method multiple time on this same cartId
        OrderDto returnValue = new OrderDto();

        CartEntity cartEntity = new CartEntity();
        CartDto cartDto = cartService.getCartCurrentOnPublicUserId(publicUserId);
        BeanUtils.copyProperties(cartDto, cartEntity);

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPublicOrderId(UUID.randomUUID().toString());
        orderEntity.setCart(cartEntity);
        orderEntity.setUser(userEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        BeanUtils.copyProperties(savedOrder, returnValue);

        //We create new cart and all items that you will add to cart in the future will be added to him while this current is permanently linked this order
        cartService.createCart(publicUserId);

        //Do not know how to implement proper transferring nested objects thats are different type in different layers(dto/response/entity)\
        //Thats why this \/

        returnValue.setCart(savedOrder.getCart());
        returnValue.setUser(savedOrder.getUser());

        return returnValue;
    }

    @Override
    public List<OrderDto> getOrdersForUser(String publicUserId) {
        List<OrderDto> returnValue = new ArrayList<>();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);


        List<OrderEntity> orders = orderRepository.findByUser(userEntity);
        for (OrderEntity orderEntity : orders) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderEntity, orderDto);
            //Do not know how to implement proper transferring nested objects thats are different type in different layers(dto/response/entity)\
            //Thats why this \/
            orderDto.setCart(orderEntity.getCart());
            orderDto.setUser(orderEntity.getUser());

            returnValue.add(orderDto);
        }

        return returnValue;
    }


}
