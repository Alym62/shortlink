package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ShortLinkException.class)
    public ProblemDetail shortLinkException(ShortLinkException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail userAlreadyException(UserAlreadyExistsException exception) {
        return exception.toProblemDetail();
    }
}
