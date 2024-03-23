package com.ziryt.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {ExceedLimitException.class})
    public ResponseEntity<Object> handleExceedLimitException(ExceedLimitException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

}
