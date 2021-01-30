package com.store.app.exception.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Cart")  // 404
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String publicId) {
        super(publicId);
    }
}
