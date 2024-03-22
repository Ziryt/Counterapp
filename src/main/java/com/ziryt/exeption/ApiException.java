package com.ziryt.exeption;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException{
    private final HttpStatus status;
    private final String message;

    private final ZonedDateTime timestamp;

    public ApiException(HttpStatus status,
                        String message,
                        ZonedDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
