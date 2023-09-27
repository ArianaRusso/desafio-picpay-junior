package com.arianarusso.desafiopicpay.core.exceptions;

public class NotFoundResourceException extends RuntimeException {
    public NotFoundResourceException(String message) {
        super(message);
    }
}
