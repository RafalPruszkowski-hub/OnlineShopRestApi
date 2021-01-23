package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.repository.CartItemRepository;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.ProductRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;

import com.store.app.exception.cart.CartNotFoundException;
import com.store.app.exception.product.ProductAlreadyInCartException;
import com.store.app.model.response.ProductResponseModel;
import com.store.app.service.CartItemService;
import com.store.app.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartItemDto create(String publicUserId, String publicProductId, CartItemDto cartItemDto) {

        CartDto cartDto = cartService.getOnPublicUserId(publicUserId);
        CartEntity cartEntity = cartRepository.findByCartId(cartDto.getCartId());
        if(cartEntity==null) throw new CartNotFoundException();


        ProductEntity productEntity = productRepository.findByPublicProductId(publicProductId);

        //Check if the record already exist in table and if yes throw exception

        //if(cartEntity.getCartItems().isEmpty()){ } else {
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            if (cartItemEntity.getProduct().getPublicProductId().equals(publicProductId)) {
                throw new ProductAlreadyInCartException(publicProductId);
            }
        }
        //}

        //creating entity that will be saved in database
        CartItemEntity cartItemEntity = new CartItemEntity(cartEntity, productEntity);
        BeanUtils.copyProperties(cartItemDto, cartItemEntity);
        cartItemEntity.setPublicCartItemId(UUID.randomUUID().toString());

        cartItemEntity.setProductsPrice(productEntity.getProductPrice()
                * cartItemDto.getQuantity());

        //saving item into database
        CartItemEntity storedCartItem = cartItemRepository.save(cartItemEntity);


        //Update total price for the cart that item is added to
        cartService.updateTotalPrice(publicUserId, cartItemEntity.getProductsPrice());


        //Creating return value
        CartItemDto returnValue = new CartItemDto(storedCartItem);

        return returnValue;
    }
}
