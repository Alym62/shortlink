package com.github.shortlink.config.dynamo;

import com.github.shortlink.core.domain.annotations.DynamoDbTableName;
import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.stereotype.Component;

@Component
public class CustomTableNameResolver implements DynamoDbTableNameResolver {
    @Override
    public <T> String resolve(Class<T> clazz) {
        return clazz.getAnnotation(DynamoDbTableName.class).name();
    }
}
