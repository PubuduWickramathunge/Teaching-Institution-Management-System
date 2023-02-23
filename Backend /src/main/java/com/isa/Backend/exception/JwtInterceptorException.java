package com.isa.Backend.exception;

public class JwtInterceptorException extends RuntimeException {
    public JwtInterceptorException(String message, Throwable cause) {
        super(message, cause);
    }
}