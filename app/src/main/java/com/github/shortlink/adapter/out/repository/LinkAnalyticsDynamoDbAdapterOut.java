package com.github.shortlink.adapter.out.repository;

import com.github.shortlink.adapter.out.mappers.LinkAnalyticsMapper;
import com.github.shortlink.adapter.out.mappers.LinkMapper;
import com.github.shortlink.adapter.out.persistence.LinkAnalyticsPersistence;
import com.github.shortlink.adapter.out.persistence.LinkPersistence;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.LinkAnalytics;
import com.github.shortlink.core.port.out.LinkAnalyticsRepositoryOut;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class LinkAnalyticsDynamoDbAdapterOut implements LinkAnalyticsRepositoryOut {
    private final DynamoDbTemplate dynamoDbTemplate;

    public LinkAnalyticsDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public void updateClickCount(Link link) {
        UpdateItemRequest updateLinkAnalyticsUpdate = UpdateItemRequest.builder().build();
        final LocalDate now = LocalDate.now();
        var key = Key.builder()
                .partitionValue(link.getLinkId())
                .sortValue(now.toString())
                .build();

        final LinkAnalyticsPersistence analytics = dynamoDbTemplate.load(key, LinkAnalyticsPersistence.class);
        if (analytics != null) {
            analytics.setUpdatedAt(LocalDateTime.now());
            dynamoDbTemplate.update(analytics);
        } else {
            dynamoDbTemplate.save(LinkAnalyticsMapper.domainLinkFromPersistenceAnalytics(link, now));
        }
    }

    @Override
    public Optional<LinkAnalytics> getAnalyticsByLinkId(String linkId) {
        final Key key = Key.builder()
                .partitionValue(linkId)
                .sortValue(LocalDate.now().toString())
                .build();

        final LinkAnalytics analytics = LinkAnalyticsMapper.persistenceToDomain(dynamoDbTemplate.load(key, LinkAnalyticsPersistence.class));
        return Optional.ofNullable(analytics);
    }

    @Override
    public void save(LinkAnalytics analytics) {
        final LinkAnalyticsPersistence domainToPersistenceSave = LinkAnalyticsMapper.domainFromPersistence(analytics);
        dynamoDbTemplate.save(domainToPersistenceSave);
    }
}
