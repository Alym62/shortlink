package com.github.shortlink.adapter.out.repository;

import com.github.shortlink.adapter.out.mappers.LinkMapper;
import com.github.shortlink.adapter.out.persistence.LinkPersistence;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.port.out.LinkRepositoryOut;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@Component
public class LinkDynamoDbAdapterOut implements LinkRepositoryOut {
    private final DynamoDbTemplate dynamoDbTemplate;

    public LinkDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public Link save(Link link) {
        final LinkPersistence persistence = LinkMapper.persistenceFromDomain(link);
        dynamoDbTemplate.save(persistence);

        return link;
    }

    @Override
    public boolean existsLinkBySlug(String slug) {
        final Key key = Key.builder()
                .partitionValue(slug)
                .build();

        return dynamoDbTemplate.load(key, LinkPersistence.class) != null;
    }
}
