package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(LinkAlreadyExistsException.class)
    public ProblemDetail linkAlreadyException(LinkAlreadyExistsException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail userNotFoundException(UserNotFoundException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(LoginException.class)
    public ProblemDetail loginException(LoginException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Parâmetros inválidos. Tente novamente");
        problemDetail.setProperty("parametros-invalidos", fieldErrors);

        return problemDetail;
    }
}
