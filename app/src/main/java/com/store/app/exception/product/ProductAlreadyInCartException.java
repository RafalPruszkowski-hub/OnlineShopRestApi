package com.store.app.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Product already inside cart")  // 404
public class ProductAlreadyInCartException extends RuntimeException{
    public ProductAlreadyInCartException(){
        super();
    }
    public ProductAlreadyInCartException(String publicId){
        super(publicId);
    }
}
