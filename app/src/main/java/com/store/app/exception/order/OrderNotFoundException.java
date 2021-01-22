package com.store.app.exception.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super();
    }

    OrderNotFoundException(String publicId){
        super(publicId);
    }
}
