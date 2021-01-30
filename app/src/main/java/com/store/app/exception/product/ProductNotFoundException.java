package com.store.app.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")  // 404
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String publicId) {
        super(publicId);
    }
}
