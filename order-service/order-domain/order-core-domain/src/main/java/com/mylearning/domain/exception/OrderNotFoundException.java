package com.mylearning.domain.exception;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
