package com.github.shortlink.adapter.in.dto;

import com.github.shortlink.core.domain.vo.UtmTags;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateLinkRequestDto(
        @NotBlank String linkSlug,
        @NotBlank String urlOriginal,
        UtmTags utm,
        LocalDateTime expirationDate
) {
}
