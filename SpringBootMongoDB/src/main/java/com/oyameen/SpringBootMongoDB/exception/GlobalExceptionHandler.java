package com.oyameen.SpringBootMongoDB.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorModel> handleExceptions(Exception exception) {
        ErrorModel errorModel = new ErrorModel(
                System.currentTimeMillis(), 400,
                "Bad Request", exception.getMessage());
        return ResponseEntity.status(400).body(errorModel);
    }

}
