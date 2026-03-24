package com.github.shortlink.adapter.in.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 8, max = 32)
        String password,
        @NotBlank
        @Length(max = 50, min = 3)
        String nickname
) {
}
