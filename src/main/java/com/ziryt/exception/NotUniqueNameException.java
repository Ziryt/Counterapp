package com.ziryt.exception;

import lombok.Getter;

@Getter
public class NotUniqueNameException extends RuntimeException{
    public NotUniqueNameException(String message) {
        super(message);
    }
}
