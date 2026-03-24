package com.github.shortlink.adapter.in.dto;

import com.github.shortlink.core.domain.User;

import java.time.LocalDateTime;

public record CreateUserResponseDto(
        String userId,
        LocalDateTime createdAt
) {
}
