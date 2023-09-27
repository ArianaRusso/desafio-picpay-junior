package com.arianarusso.desafiopicpay.core.exceptions;

public class TransactionNotAuthorized extends RuntimeException {
    public TransactionNotAuthorized(String message) {
        super(message);
    }
}
