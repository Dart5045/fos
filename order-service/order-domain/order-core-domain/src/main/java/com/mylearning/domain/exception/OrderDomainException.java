package com.mylearning.domain.exception;

import com.mylearning.domain.exception.DomainException;

public class OrderDomainException  extends DomainException{


    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDomainException(String message) {
        super(message);
    }
}
