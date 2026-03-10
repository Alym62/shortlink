package com.github.shortlink.adapter.in.dto;

import com.github.shortlink.core.domain.User;

import java.time.LocalDateTime;

public record CreateUserResponseDto(
        String userId,
        LocalDateTime createdAt
) {
    public static CreateUserResponseDto fromDomain(User user) {
        return new CreateUserResponseDto(user.getUserId().toString(), user.getCreatedAt());
    }
}
