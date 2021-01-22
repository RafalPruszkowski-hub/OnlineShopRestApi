package com.store.app.exception.CartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(){
        super();
    }
    public CartItemNotFoundException(String publicId){
        super(publicId);
    }
}
