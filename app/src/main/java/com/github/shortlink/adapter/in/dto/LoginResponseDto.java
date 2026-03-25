package com.github.shortlink.adapter.in.dto;

public record LoginResponseDto(
        String accessToken,
        Long expiresIn
) {
}
