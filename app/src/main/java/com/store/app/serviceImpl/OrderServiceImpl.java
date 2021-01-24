package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.OrderRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.*;
import com.store.app.exception.cart.CartIsEmptyException;
import com.store.app.exception.cart.CartNotFoundException;

import com.store.app.exception.NotAuthorizedException;
import com.store.app.exception.order.OrderNotFoundException;
import com.store.app.exception.product.ProductOutOfStockException;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.service.CartService;
import com.store.app.service.OrderService;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
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
    public OrderDto create(String email) {
        OrderEntity orderEntity = new OrderEntity();

        //Set user
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity==null) throw new UserNotFoundException();
        orderEntity.setUser(userEntity);

        //Set cart
        CartDto cartDto = cartService.getOnPublicUserId(userEntity.getPublicUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartDto.getCartId());
        if(cartEntity ==null) throw new CartNotFoundException();

        orderEntity.setCart(cartEntity);
        orderEntity.setPublicOrderId(UUID.randomUUID().toString());

        //Check if cart does not have more item then in stock
        checkIfEnoughItemsInStock(cartDto);

        //Check if cart contains any car items
        checkIfNotEmpty(cartDto);

        //Save Order
        orderRepository.save(orderEntity);

        updateProductsInStock(cartDto);

        cartService.create(userEntity.getPublicUserId());

        //Set and save cart for order
        cartService.saveForOrder(orderEntity.getCart().getCartId(), orderEntity.getOrderId());


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
            throw new CartIsEmptyException();
    }

    private void checkIfEnoughItemsInStock(CartDto cartDto) {
        for (CartItemDto cartItemTmp : cartDto.getCartItems()) {
            int stock = cartItemTmp.getProduct().getQuantityOfStock();
            if (stock < cartItemTmp.getQuantity())
                throw new ProductOutOfStockException(cartItemTmp.getProduct().getPublicProductId());
        }
    }

    @Override
    public OrderDto get(String email, String publicOrderId) {
        UserEntity userEntity = userRepository.findByEmail(email);
        OrderEntity orderEntity = orderRepository.findByPublicOrderId(publicOrderId);
        if (orderEntity==null) throw new OrderNotFoundException();
        if (orderEntity.getUser().getUserId()==userEntity.getUserId()) throw new NotAuthorizedException();

        OrderDto returnValue = new OrderDto(orderEntity);
        return returnValue;
    }

    @Override
    public List<OrderDto> getList(String email, int page, int limit) {
        List returnValue = new ArrayList<OrderDto>();

        //UserEntity userEntity = userRepository.findByEmail(email);

        Pageable pageableRequest = PageRequest.of(page, limit);
        // TODO Write Query that will not need this if
        Page<OrderEntity> productPage = orderRepository.findAll(pageableRequest);
        List<OrderEntity> orders = productPage.getContent();

        for (OrderEntity orderEntity : orders) {
            if(orderEntity.getUser().getEmail().equals(email)){
                OrderDto orderDto = new OrderDto(orderEntity);
                returnValue.add(orderDto);
            }
        }

        return returnValue;
    }
}
