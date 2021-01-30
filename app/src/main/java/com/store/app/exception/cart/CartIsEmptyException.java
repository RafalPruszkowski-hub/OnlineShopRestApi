package com.store.app.exception.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Cart is empty")  // 404
public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException() {
    }
}
