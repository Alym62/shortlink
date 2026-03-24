package com.github.shortlink.adapter.in.dto;

public record CreateUserRequestDto(
        String email,
        String password,
        String nickname
) {
}
