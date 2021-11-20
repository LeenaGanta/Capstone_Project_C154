package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED, reason = "Low Balance")
public class LowBalanceException extends RuntimeException {
    public LowBalanceException(String message) {
        super(message);
    }
}