package com.ra7eeb.assessment.exceptions;
import java.time.ZonedDateTime;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpErrorInfo {
    private final ZonedDateTime timestamp;
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo() {
        timestamp = null;
        this.httpStatus = null;
        this.message = null;
    }

    public HttpErrorInfo(HttpStatus httpStatus, String message) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
