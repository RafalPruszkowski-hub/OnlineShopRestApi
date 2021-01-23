package com.store.app.exception.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Product already inside cart")  // 404
public class CartIsEmptyException  extends RuntimeException{
    public CartIsEmptyException(){}
}
