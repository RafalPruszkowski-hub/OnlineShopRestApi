package com.store.app.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Product out of stock")  // 404
public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(){
        super();
    }
    public ProductOutOfStockException(String productPublicId){
        super(productPublicId);
    }
}
