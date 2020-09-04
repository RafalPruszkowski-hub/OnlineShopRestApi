package com.store.app.service;

import com.store.app.dto.CartItemDto;
import com.store.app.dto.CartItemDtoReturnCreating;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {
    CartItemDtoReturnCreating createCartItem(String publicUserId, String publicProductId, CartItemDto cartItemDto);
}
