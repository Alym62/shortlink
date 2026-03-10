package com.github.shortlink.adapter.in.dto;

import com.github.shortlink.core.domain.User;

public record CreateUserRequestDto(
        String email,
        String password,
        String nickname
) {
    public static User toDomain(CreateUserRequestDto requestDto) {
        return new User(requestDto.email, requestDto.password, requestDto.nickname);
    }
}
