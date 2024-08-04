package com.lyyang.exception;

public class BBException extends RuntimeException {
    private String message;

    public BBException(String message) {
        super(message);
        this.message = message;
    }

    public BBException() {
    }
}