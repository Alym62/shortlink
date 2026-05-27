package com.github.shortlink.core.domain.vo;

public record UtmTags(
        String utmSource,
        String utmMedium,
        String utmCampaign,
        String utmContent
) {
}
