package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ShortLinkBusinessException extends ShortLinkException {
    private final String message;

    public ShortLinkBusinessException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Ops! Erro de negócio.");
        problemDetail.setDetail(message);

        return problemDetail;
    }
}
