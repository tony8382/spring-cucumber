package com.lyyang.exception;

public class AAException extends RuntimeException {
    private String message;

    public AAException(String message) {
        super(message);
        this.message = message;
    }

    public AAException() {
    }
}