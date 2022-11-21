package com.avinty.hr.exception;

import com.avinty.hr.model.exception.ApiExceptionResponse;
import com.avinty.hr.model.exception.SubError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException exp) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .errorMessage(exp.getMessage())
                .errorCode(-1)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {

        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .errorMessage("Validation Error")
                .errorCode(-1)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timeStamp(ZonedDateTime.now(ZoneId.of("Z")))
                .subErrorList(exp.getAllErrors().stream()
                        .map(subExp -> new SubError(subExp.getDefaultMessage(), 0))
                        .collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
