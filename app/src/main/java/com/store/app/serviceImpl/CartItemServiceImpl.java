package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.CartItemRepository;
import com.store.app.database.repository.CartRepository;
import com.store.app.database.repository.ProductRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import com.store.app.exception.cart.CartNotFoundException;
import com.store.app.exception.product.ProductAlreadyInCartException;
import com.store.app.exception.user.UserNotFoundException;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public CartItemDto create(String email, String publicProductId, CartItemDto cartItemDto) {

        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException(email);

        CartDto cartDto = cartService.getOnPublicUserId(userEntity.getPublicUserId());
        CartEntity cartEntity = cartRepository.findByCartId(cartDto.getCartId());
        if (cartEntity == null) throw new CartNotFoundException();


        ProductEntity productEntity = productRepository.findByPublicProductId(publicProductId);

        //Check if the record already exist in table and if yes throw exception
        checkIfAlreadyInCart(publicProductId, cartDto);

        //creating entity that will be saved in database
        CartItemEntity cartItemEntity = new CartItemEntity(cartEntity, productEntity);
        BeanUtils.copyProperties(cartItemDto, cartItemEntity);
        cartItemEntity.setPublicCartItemId(UUID.randomUUID().toString());

        cartItemEntity.setProductsPrice(productEntity.getProductPrice()
                * cartItemDto.getQuantity());


        CartItemEntity storedCartItem = cartItemRepository.save(cartItemEntity);
        cartService.updateTotalPrice(userEntity.getPublicUserId(), cartItemEntity.getProductsPrice());

        CartItemDto returnValue = new CartItemDto(storedCartItem);
        return returnValue;
    }

    private void checkIfAlreadyInCart(String publicProductId, CartDto cartDto) {
        for (CartItemDto cartItemDto : cartDto.getCartItems()) {
            if (cartItemDto.getProduct().getPublicProductId().equals(publicProductId)) {
                throw new ProductAlreadyInCartException(publicProductId);
            }
        }
    }
}
