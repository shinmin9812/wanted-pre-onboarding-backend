package com.example.wanted.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    HttpErrorInfo handleNotFoundExceptions(ServerHttpRequest request, Exception ex) {
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ExistsApplyException.class)
    public @ResponseBody
    HttpErrorInfo handleBadRequestExceptions(ServerHttpRequest request, Exception ex) {
        return createHttpErrorInfo(BAD_REQUEST, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception ex) {
        final String message = ex.getMessage();

        return new HttpErrorInfo(httpStatus, message);
    }
}
