package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.LinkAnalytics;

import java.util.Optional;

public interface LinkAnalyticsRepositoryOut {
    void updateClickCount(Link link);

    Optional<LinkAnalytics> getAnalyticsByLinkId(String linkId);

    void save(LinkAnalytics analytics);
}
