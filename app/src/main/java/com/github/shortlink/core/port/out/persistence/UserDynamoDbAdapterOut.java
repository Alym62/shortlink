package com.github.shortlink.core.port.out.persistence;

import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryOut {
    private final DynamoDbTemplate dynamoDbTemplate;

    public UserDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User save(User user) {
        final UserPersistence mapFromDomain = UserPersistence.fromDomain(user);
        dynamoDbTemplate.save(mapFromDomain);

        return user;
    }
}
