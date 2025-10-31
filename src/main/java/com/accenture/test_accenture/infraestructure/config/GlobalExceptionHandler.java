package com.accenture.test_accenture.infraestructure.config;

import com.accenture.test_accenture.infraestructure.exceptions.ErrorResponse;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        return new ErrorResponse("NOT_FOUND",ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
