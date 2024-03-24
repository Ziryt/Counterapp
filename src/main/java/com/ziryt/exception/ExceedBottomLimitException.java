package com.ziryt.exception;

import lombok.Getter;

@Getter
public class ExceedBottomLimitException extends RuntimeException {
    private final int id;
    private final long value;
    public ExceedBottomLimitException(String message, long value, int id) {
        super(message);
        this.id = id;
        this.value = value;
    }
}
