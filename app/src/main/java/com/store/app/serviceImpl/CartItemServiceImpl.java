package com.store.app.serviceImpl;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.repository.CartItemRepository;
import com.store.app.database.repository.ProductRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import com.store.app.service.CartItemService;
import com.store.app.service.CartService;
import com.store.app.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @Override
    public CartItemDto createItem(CartItemDto cartItemDto, String publicProductId, String publicUserId) {

        //TODO not allow for multiple time this same product input to one cart
        //TODO not allow in stock go -1 on product
        CartItemDto returnValue = new CartItemDto();
        String publicID = UUID.randomUUID().toString();

        CartEntity cartEntity = new CartEntity();
        CartDto cartDto = cartService.getCartCurrentOnPublicUserId(publicUserId);
        BeanUtils.copyProperties(cartDto, cartEntity);

        ProductEntity productEntity = productRepository.findByPublicProductId(publicProductId);

        CartItemEntity cartItemEntity = new CartItemEntity(cartEntity, productEntity);
        BeanUtils.copyProperties(cartItemDto, cartItemEntity);

        cartItemEntity.setPublicCartItemId(publicID);

        double productPrice = cartItemEntity.getQuantity() * cartItemEntity.getProduct().getProductPrice();
        cartItemEntity.setProductsPrice(productPrice);

        productService.updateProductAmount(cartItemDto.getQuantity(),
                publicProductId);

        CartItemEntity createdCartItem = cartItemRepository.save(cartItemEntity);

        cartService.updateTotalPrice(publicUserId, createdCartItem.getProductsPrice());

        BeanUtils.copyProperties(createdCartItem, returnValue);
        returnValue.setProduct(createdCartItem.getProduct());

        return returnValue;
    }
}
