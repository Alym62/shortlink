package com.github.shortlink.adapter.in.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
