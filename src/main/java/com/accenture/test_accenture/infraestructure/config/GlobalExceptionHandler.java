package com.accenture.test_accenture.infraestructure.config;

import com.accenture.test_accenture.infraestructure.exceptions.ErrorResponse;
import com.accenture.test_accenture.infraestructure.exceptions.NotFoundException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public Mono<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return Mono.just(
                new ErrorResponse("NOT_FOUND",ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ErrorResponse> handleGenericException(Exception ex) {
        return Mono.just(
                new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler({
            HttpClientErrorException.BadRequest.class,
            DuplicateKeyException.class,
            ServerWebInputException.class,
            WebExchangeBindException.class,
    })
    public Mono<ErrorResponse> handlerBadRequestException(Exception ex) {
        return Mono.just(
                new ErrorResponse("BAD_REQUEST", ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public Mono<ErrorResponse> handlerNotAllowedException(Exception ex) {
        return Mono.just(
                new ErrorResponse("METHOD_NOT_ALLOWED", ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value()));

    }
}
