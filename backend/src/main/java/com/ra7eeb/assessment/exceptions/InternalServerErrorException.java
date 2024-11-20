package com.ra7eeb.assessment.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    public InternalServerErrorException(String message) {
        super(message);
    }
}
