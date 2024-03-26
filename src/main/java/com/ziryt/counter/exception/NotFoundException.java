package com.ziryt.counter.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final int id;
    public NotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }
}
