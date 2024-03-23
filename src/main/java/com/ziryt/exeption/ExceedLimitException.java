package com.ziryt.exeption;

public class ExceedLimitException extends RuntimeException {
    public ExceedLimitException(String message) {
        super(message);
    }
}
