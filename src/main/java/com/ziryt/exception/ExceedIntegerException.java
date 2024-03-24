package com.ziryt.exception;

import lombok.Getter;

@Getter
public class ExceedIntegerException extends RuntimeException {
    private final int id;
    private final long value;
    public ExceedIntegerException(String message, long value, int id) {
        super(message);
        this.id = id;
        this.value = value;
    }
}
