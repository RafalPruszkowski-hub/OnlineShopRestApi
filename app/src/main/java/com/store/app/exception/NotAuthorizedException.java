package com.store.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not authorized")  // 403
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super();
    }
}
