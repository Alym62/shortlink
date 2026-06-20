package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.exceptions.LinkAlreadyExistsException;
import com.github.shortlink.adapter.in.exceptions.ShortLinkBusinessException;
import com.github.shortlink.adapter.out.mappers.LinkAnalyticsMapper;
import com.github.shortlink.adapter.out.persistence.LinkAnalyticsPersistence;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.LinkAnalytics;
import com.github.shortlink.core.port.in.ShortLinkPortIn;
import com.github.shortlink.core.port.in.UpdateClicksPortIn;
import com.github.shortlink.core.port.out.LinkAnalyticsRepositoryOut;
import com.github.shortlink.core.port.out.LinkRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateClicksUseCase implements UpdateClicksPortIn {
    private static final Logger logger = LoggerFactory.getLogger(UpdateClicksUseCase.class);

    private final LinkAnalyticsRepositoryOut linkAnalyticsRepositoryOut;

    public UpdateClicksUseCase(LinkAnalyticsRepositoryOut linkAnalyticsRepositoryOut) {
        this.linkAnalyticsRepositoryOut = linkAnalyticsRepositoryOut;
    }

    @Override
    public void execute(Link link) {
        final Optional<LinkAnalytics> analytics = linkAnalyticsRepositoryOut.getAnalyticsByLinkId(link.getLinkId());
        if (analytics.isPresent()) {
            linkAnalyticsRepositoryOut.updateClickCount();
        }

        final LinkAnalyticsPersistence analyticsPersistenceMapped = LinkAnalyticsMapper.domainLinkFromPersistenceAnalytics(link, LocalDate.now())
        linkAnalyticsRepositoryOut.save(LinkAnalyticsMapper.persistenceToDomain(analyticsPersistenceMapped));
    }
}
