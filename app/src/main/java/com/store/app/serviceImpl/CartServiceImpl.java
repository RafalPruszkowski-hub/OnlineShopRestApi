package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.CartItemRepository;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.OrderRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import com.store.app.dto.ProductDto;
import com.store.app.dto.UserDto;
import com.store.app.exception.cart.CartNotFoundException;
import com.store.app.exception.order.OrderNotFoundException;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public CartDto getOnPublicUserId(String publicUserId) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if(userEntity == null) throw new UserNotFoundException(publicUserId);

        int cartId = cartRepository.getCurrentCartEntityForUser(userEntity.getUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if(cartEntity == null) throw new CartNotFoundException();


        BeanUtils.copyProperties(cartEntity, returnValue);

        //Setting user as dto for return value
        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(cartEntity.getUser(), returnUser);
        returnValue.setUser(returnUser);

        //Setting list of carts as list of dto items for return value
        List<CartItemDto> returnItems = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            CartItemDto cartItemDto = new CartItemDto();
            BeanUtils.copyProperties(cartItemEntity, cartItemDto);
            //to not allow infinite loop occurs while senting cartItems to return value
            cartItemDto.setCart(null);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(cartItemEntity.getProduct(), productDto);
            cartItemDto.setProduct(productDto);
            returnItems.add(cartItemDto);
        }
        returnValue.setCartItems(returnItems);

        return returnValue;
    }

    @Override
    public CartDto getOnUserEmail(String email) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UserNotFoundException();

        int cartId = cartRepository.getCurrentCartEntityForUser(userEntity.getUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if(cartEntity == null) throw new CartNotFoundException();


        BeanUtils.copyProperties(cartEntity, returnValue);

        //Setting user as dto for return value
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        returnValue.setUser(userDto);

        //Setting list of carts as list of dto items for return value
        List<CartItemDto> returnItems = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            CartItemDto cartItemDto = new CartItemDto();
            BeanUtils.copyProperties(cartItemEntity, cartItemDto);
            //to not allow infinite loop occurs while senting cartItems to return value
            cartItemDto.setCart(null);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(cartItemEntity.getProduct(), productDto);
            cartItemDto.setProduct(productDto);
            returnItems.add(cartItemDto);
        }
        returnValue.setCartItems(returnItems);

        return returnValue;
    }



    //SOMEHOW NOT WORKING FIX IT PLS
    @Override
    public void updateTotalPrice(String publicUserId, double price) {

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);
        if (userEntity == null) throw new UserNotFoundException(publicUserId);

        int cartId = cartRepository.getCurrentCartEntityForUser(userEntity.getUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if(cartEntity==null) throw new CartNotFoundException();

        double tmp = price;

        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            tmp += cartItemEntity.getProductsPrice();
        }

        cartEntity.setTotalPrice(tmp);
        cartRepository.save(cartEntity);
    }

    @Override
    public void saveForOrder(int cartId, int orderId) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if(cartEntity ==null )throw new CartNotFoundException();

        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity==null) throw new OrderNotFoundException();

        cartEntity.setOrderEntity(orderEntity);
        cartRepository.save(cartEntity);
    }

    @Override
    public CartDto create(String userId) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByPublicUserId(userId);
        if(userEntity == null) throw new UserNotFoundException();

        CartEntity cartEntity = new CartEntity(userEntity);
        cartEntity.setPublicCartId(UUID.randomUUID().toString());

        CartEntity storedCart = cartRepository.save(cartEntity);
        BeanUtils.copyProperties(storedCart, returnValue);

        return returnValue;
    }
}
