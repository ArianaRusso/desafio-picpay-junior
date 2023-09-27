package com.arianarusso.desafiopicpay.core;

public class ErrorConflictException extends RuntimeException {
    public ErrorConflictException(String message){
        super(message);
    }
}
