package com.ziryt.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiException(
        HttpStatus status,
        String message,
        ZonedDateTime timestamp
) {
}
