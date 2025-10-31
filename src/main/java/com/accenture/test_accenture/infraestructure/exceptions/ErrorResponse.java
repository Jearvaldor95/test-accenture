package com.accenture.test_accenture.infraestructure.exceptions;

public record ErrorResponse(String error, String message, int status) {
}
