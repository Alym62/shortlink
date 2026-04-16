package com.github.shortlink.adapter.out.persistence;

import com.github.shortlink.adapter.out.mappers.UserMapper;
import com.github.shortlink.core.commons.Constants;
import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryOut {
    private final DynamoDbTemplate dynamoDbTemplate;

    public UserDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User save(User user) {
        final UserPersistence mapFromDomain = UserMapper.persistenceFromDomain(user);
        dynamoDbTemplate.save(mapFromDomain);

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final QueryConditional conditional = QueryConditional.keyEqualTo(key ->
                key.partitionValue(AttributeValue.builder().s(email).build()));
        final QueryEnhancedRequest query = QueryEnhancedRequest.builder()
                .queryConditional(conditional)
                .build();

        final PageIterable<UserPersistence> result = dynamoDbTemplate.query(query, UserPersistence.class, Constants.EMAIL_INDEX);
        return result.stream()
                .flatMap(userPersistencePage -> userPersistencePage.items().stream())
                .map(UserMapper::persistenceToDomain)
                .findFirst();
    }

    @Override
    public void deleteById(UUID userId) {
        final Key key = Key.builder()
                .partitionValue(userId.toString())
                .build();
        dynamoDbTemplate.delete(key, UserPersistence.class);
    }

    @Override
    public boolean existsUserById(UUID userId) {
        final Key key = Key.builder()
                .partitionValue(userId.toString())
                .build();

        return dynamoDbTemplate.load(key, UserPersistence.class) != null;
    }
}
