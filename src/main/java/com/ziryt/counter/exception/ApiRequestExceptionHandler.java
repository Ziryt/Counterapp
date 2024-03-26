package com.ziryt.counter.exception;

import com.ziryt.counter.controller.CounterController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class ApiRequestExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        log.error("counter with id={} not found", e.getId());
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {ExceedIntegerException.class})
    public ResponseEntity<Object> handleExceedLimitException(ExceedIntegerException e) {
        log.error("value={} of counter with id={} exceeds integer limit", e.getValue(), e.getId());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {ExceedTopLimitException.class})
    public ResponseEntity<Object> handleExceedLimitException(ExceedTopLimitException e) {
        log.error("value={} of counter with id={} will exceed counters top limit", e.getValue(), e.getId());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {ExceedBottomLimitException.class})
    public ResponseEntity<Object> handleExceedLimitException(ExceedBottomLimitException e) {
        log.error("value={} of counter with id={} will exceed counters bottom limit", e.getValue(), e.getId());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {NotUniqueNameException.class})
    public ResponseEntity<Object> handleNotUniqueNameException(NotUniqueNameException e) {
        log.error("Not unique name was provided for new counter");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
            PropertyValueException.class})
    public ResponseEntity<Object> handleValidationExceptions(Exception e) {
        log.error("invalid data format was sent");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

}
