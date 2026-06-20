package com.github.shortlink.adapter.out.mappers;

import com.github.shortlink.adapter.out.persistence.LinkAnalyticsPersistence;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.LinkAnalytics;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class LinkAnalyticsMapper {
    private LinkAnalyticsMapper() {
    }

    public static LinkAnalyticsPersistence domainLinkFromPersistenceAnalytics(Link link, LocalDate date) {
        final LinkAnalyticsPersistence persistence = new LinkAnalyticsPersistence();
        persistence.setLinkId(link.getLinkId());
        persistence.setDate(date);
        persistence.setClicks(1L);
        persistence.setCreatedAt(LocalDateTime.now());
        persistence.setUpdatedAt(LocalDateTime.now());

        return persistence;
    }

    public static LinkAnalytics persistenceToDomain(LinkAnalyticsPersistence persistence) {
        final LinkAnalytics domain = new LinkAnalytics();
        domain.setLinkId(persistence.getLinkId());
        domain.setDate(persistence.getDate());
        domain.setClicks(persistence.getClicks());
        domain.setCreatedAt(persistence.getCreatedAt());
        domain.setUpdatedAt(persistence.getUpdatedAt());

        return domain;
    }

    public static LinkAnalyticsPersistence domainFromPersistence(LinkAnalytics domain) {
        final LinkAnalyticsPersistence persistence = new LinkAnalyticsPersistence();
        persistence.setLinkId(domain.getLinkId());
        persistence.setDate(domain.getDate());
        persistence.setClicks(domain.getClicks());
        persistence.setCreatedAt(domain.getCreatedAt());
        persistence.setUpdatedAt(domain.getUpdatedAt());

        return persistence;
    }
}
