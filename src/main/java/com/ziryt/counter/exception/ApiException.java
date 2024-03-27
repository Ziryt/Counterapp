package com.ziryt.counter.exception;

import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatusCode;


import java.time.ZonedDateTime;

public record ApiException(
        ZonedDateTime timestamp,
        int statusCode,
        HttpStatusCode status,
        String message
) {
}
