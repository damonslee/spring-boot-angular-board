package com.tram.springbootangularboard.exception;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String msg) {
        super(msg);
    }
}
