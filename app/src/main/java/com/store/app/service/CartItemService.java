package com.store.app.service;

import com.store.app.dto.CartItemDto;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {
    CartItemDto create(String publicUserId, String publicProductId, CartItemDto cartItemDto);
}
