package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.CartItemRepository;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CartDto getCartCurrentOnPublicUserId(String publicUserId) {
        CartDto returnValue = new CartDto();
        returnValue = updateTotalPrice(publicUserId);

        return returnValue;
    }

    @Override
    public CartDto createCart(String userId) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByPublicUserId(userId);

        CartEntity cartEntity = new CartEntity(userEntity);
        cartEntity.setPublicCartId(UUID.randomUUID().toString());


        CartEntity storedCart = cartRepository.save(cartEntity);

        BeanUtils.copyProperties(storedCart, returnValue);

        return returnValue;
    }

    public CartDto createCart(int userId) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByUserId(userId);

        CartEntity cartEntity = new CartEntity(userEntity);
        cartEntity.setPublicCartId(UUID.randomUUID().toString());

        CartEntity storedCart = cartRepository.save(cartEntity);
        BeanUtils.copyProperties(storedCart, returnValue);

        return returnValue;
    }


    @Override
    public CartDto updateTotalPrice(String publicUserId) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        int cartId = cartRepository.getCurrentCartEntityForUser(userEntity.getUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartId);

        List<CartItemEntity> items = cartEntity.getCartItems();

        double totalPrice = 0;
        for (CartItemEntity cartItemEntity : items) {
            double x = cartItemEntity.getProductsPrice();
            totalPrice += x;
        }
        cartEntity.setTotalPrice(totalPrice);
        CartEntity storedCartEntity = new CartEntity();
        storedCartEntity = cartRepository.save(cartEntity);
        BeanUtils.copyProperties(storedCartEntity, returnValue);

        return returnValue;
    }

    @Override
    public CartDto updateTotalPrice(String publicUserId, double newProductPrice) {
        CartDto returnValue = new CartDto();

        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        int cartId = cartRepository.getCurrentCartEntityForUser(userEntity.getUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartId);

        List<CartItemEntity> items = cartEntity.getCartItems();

        double totalPrice = newProductPrice;
        for (CartItemEntity cartItemEntity : items) {
            double x = cartItemEntity.getProductsPrice();
            totalPrice += x;
        }
        cartEntity.setTotalPrice(totalPrice);

        CartEntity storedCartEntity = cartRepository.save(cartEntity);
        BeanUtils.copyProperties(storedCartEntity, returnValue);

        return returnValue;
    }


    @Override
    public CartDto getCart(String publicCartId) {
        CartDto returnValue = new CartDto();

        CartEntity cartEntity = cartRepository.findByPublicCartId(publicCartId);
        BeanUtils.copyProperties(cartEntity, returnValue);

        return returnValue;
    }
}
