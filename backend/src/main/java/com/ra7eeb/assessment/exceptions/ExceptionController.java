package com.ra7eeb.assessment.exceptions;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionController {
    Logger logger = LoggerFactory.getLogger(ExceptionController.class);


    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody HttpErrorInfo handleForbiddenException(ForbiddenException ex) {

        return createHttpErrorInfo(HttpStatus.FORBIDDEN, ex.getMessage());
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody HttpErrorInfo handleInvalidInputException(BadRequestException ex) {

        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, ex.getMessage());

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody HttpErrorInfo handleValidationErrors(MethodArgumentNotValidException ex) {
        JSONObject errors = new JSONObject();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String message = errors.toJSONString();
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public @ResponseBody HttpErrorInfo handleNotFoundException(ResourceNotFoundException ex) {
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, ex.getMessage());

    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody HttpErrorInfo handleInternalServerError(InternalServerErrorException ex) {
        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

    }
    private HttpErrorInfo createHttpErrorInfo(
            HttpStatus httpStatus, String message) {
        logger.error("Returning HTTP status: {} for path: {}, message: {}", httpStatus, message);
        return new HttpErrorInfo(httpStatus,  message);
    }


}
