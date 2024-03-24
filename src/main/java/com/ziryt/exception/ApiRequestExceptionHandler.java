package com.ziryt.exception;

import com.ziryt.counter.CounterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(CounterController.class);
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        logger.warn("counter with id={} not found", e.getId());
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
        logger.warn("value={} of counter with id={} exceeds integer limit", e.getValue(), e.getId());
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
        logger.warn("value={} of counter with id={} will exceed counters top limit", e.getValue(), e.getId());
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
        logger.warn("value={} of counter with id={} will exceed counters bottom limit", e.getValue(), e.getId());
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
        logger.warn("invalid data format was sent");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                status,
                e.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

}
