package com.github.shortlink.adapter.in.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LinkNotFoundException extends ShortLinkException {
    private final String message;

    public LinkNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Ops! Link não encontrado.");
        problemDetail.setDetail(message);

        return problemDetail;
    }
}
