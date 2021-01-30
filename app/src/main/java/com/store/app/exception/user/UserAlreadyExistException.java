package com.store.app.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exist with following email")  // 409
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String email) {
        super(email);
    }
}
