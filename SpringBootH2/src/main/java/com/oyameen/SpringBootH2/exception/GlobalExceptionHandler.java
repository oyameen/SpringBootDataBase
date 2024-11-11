package com.oyameen.SpringBootH2.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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

    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity<ErrorModel> handleSQLException(DataAccessException exception) {
        ErrorModel errorModel = new ErrorModel(
                System.currentTimeMillis(), 400,
                "Bad Request due to DataBase Exception.",
                "Failed to handle the request due to database exception raised while processing the request.");
        return ResponseEntity.badRequest().body(errorModel);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<ErrorModel> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        ErrorModel errorModel = new ErrorModel();
        errorModel.setTimeStamp(System.currentTimeMillis());
        errorModel.setStatus(400);
        errorModel.setError("Bad Request due to DataIntegrityViolationException.");
        if (exception.getMessage().contains("FKTD229UUBV9KAJUUVJHUU6IDC4")) {
            errorModel.setMessage("Failed to handle the request because the entity has foreign keys not resolved.");
        } else {
            errorModel.setMessage("Failed to handle the request due to " + exception.getMessage());
        }
        return ResponseEntity.badRequest().body(errorModel);
    }
}
