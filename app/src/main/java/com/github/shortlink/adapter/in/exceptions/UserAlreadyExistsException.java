package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistsException extends ShortLinkException {
    private final String message;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Ops! Usuário já existe em nossa base de dados.");
        problemDetail.setDetail(message);

        return problemDetail;
    }
}
