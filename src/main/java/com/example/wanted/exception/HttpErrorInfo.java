package com.example.wanted.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpErrorInfo {
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
