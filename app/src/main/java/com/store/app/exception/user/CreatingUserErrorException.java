package com.store.app.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error while creating user")  // 500
public class CreatingUserErrorException extends RuntimeException {
    public CreatingUserErrorException() {
        super();
    }

    public CreatingUserErrorException(String publicId) {
        super(publicId);
    }
}
