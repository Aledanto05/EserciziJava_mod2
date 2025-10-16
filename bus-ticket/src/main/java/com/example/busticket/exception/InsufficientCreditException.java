package com.example.busticket.exception;

public class InsufficientCreditException extends RuntimeException {

    public InsufficientCreditException(String message) {
        super(message);
    }
}
