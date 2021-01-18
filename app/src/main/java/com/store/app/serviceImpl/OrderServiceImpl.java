package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.OrderRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.*;
import com.store.app.model.response.*;
import com.store.app.service.CartService;
import com.store.app.service.OrderService;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public OrderDto createOrder(String publicUserId) {
        OrderEntity orderEntity = new OrderEntity();

        //Set user
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        orderEntity.setUser(userEntity);

        //Set cart
        CartDto cartDto = cartService.getCartCurrentOnPublicUserId(publicUserId);
        CartEntity cartEntity = cartRepository.findByCartId(cartDto.getCartId());
        orderEntity.setCart(cartEntity);

        //Set publicOrderId
        orderEntity.setPublicOrderId(UUID.randomUUID().toString());

        //Check if cart does not have more item then in stock
        checkIfEnoughtItemInStock(cartDto);

        //Check if cart contains any car items
        checkIfNotEmpty(cartDto);

        //Save Order
        orderRepository.save(orderEntity);

        //Update Products stock
        updateProductsInStock(cartDto);

        //Create New Cart
        cartService.createCart(publicUserId);

        //Set and save cart for order
        cartService.saveCartForOrder(orderEntity.getCart().getCartId(), orderEntity.getOrderId());


        OrderDto returnValue = new OrderDto(orderEntity);
        return returnValue;
    }

    private void updateProductsInStock(CartDto cartDto) {
        for (CartItemDto cartItemTmp : cartDto.getCartItems()) {
            productService.updateProductStock(cartItemTmp.getQuantity(), cartItemTmp.getProduct().getPublicProductId());
        }
    }

    private void checkIfNotEmpty(CartDto cartDto) {
        if (cartDto.getCartItems().isEmpty())
            throw new RuntimeException("Your cart is empty please put some item into it before making a order");
    }

    private void checkIfEnoughtItemInStock(CartDto cartDto) {
        for (CartItemDto cartItemTmp : cartDto.getCartItems()) {
            int stock = cartItemTmp.getProduct().getQuantityOfStock();
            if (stock < cartItemTmp.getQuantity())
                throw new RuntimeException("Sorry but you have put more products to cart then there is in stock " + cartItemTmp.getProduct().getPublicProductId());
        }
    }

    @Override
    public OrderDto getOrder(String publicOrderId) {
        OrderEntity orderEntity = orderRepository.findByPublicOrderId(publicOrderId);
        OrderDto returnValue = new OrderDto(orderEntity);

        return returnValue;
    }

    @Override
    public List<OrderDto> getOrders(String publicUserId,
                                              int page, int limit) {
        List returnValue = new ArrayList<OrderDto>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<OrderEntity> productPage = orderRepository.findAll(pageableRequest);
        List<OrderEntity> orders = productPage.getContent();

        for (OrderEntity orderEntity : orders) {
            OrderDto orderDto = new OrderDto(orderEntity);
            returnValue.add(orderDto);
        }

        return returnValue;
    }
}
