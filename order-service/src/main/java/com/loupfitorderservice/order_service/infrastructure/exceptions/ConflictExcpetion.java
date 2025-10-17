package com.loupfitorderservice.order_service.infrastructure.exceptions;

public class ConflictExcpetion extends RuntimeException {

    public ConflictExcpetion(String message) {
        super(message);
    }

    public ConflictExcpetion(String message, Throwable throwable) {
        super(message, throwable);
    }

}
