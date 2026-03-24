package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class ShortLinkException extends RuntimeException {
    private final String message;

    public ShortLinkException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ProblemDetail toProblemDetail() {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Erro interno de servidor");
        problemDetail.setDetail("Ocorreu um erro ao tentar processar a requisição no servidor");

        return problemDetail;
    }
}
