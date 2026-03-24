package com.github.shortlink.adapter.out.mappers;

import com.github.shortlink.adapter.in.dto.CreateUserRequestDto;
import com.github.shortlink.adapter.in.dto.CreateUserResponseDto;
import com.github.shortlink.adapter.out.persistence.UserPersistence;
import com.github.shortlink.core.domain.User;

public final class UserMapper {
    private UserMapper() {
    }

    public static User dtoRequestToDomain(CreateUserRequestDto requestDto) {
        return new User(requestDto.email(), requestDto.password(), requestDto.nickname());
    }

    public static CreateUserResponseDto dtoResponseFromDomain(User user) {
        return new CreateUserResponseDto(user.getUserId().toString(), user.getCreatedAt());
    }

    public static UserPersistence persistenceFromDomain(User user) {
        final UserPersistence userPersistence = new UserPersistence();
        userPersistence.setUserId(user.getUserId());
        userPersistence.setEmail(user.getEmail());
        userPersistence.setPassword(user.getPassword());
        userPersistence.setNickname(user.getNickname());
        userPersistence.setCreatedAt(user.getCreatedAt());
        userPersistence.setUpdatedAt(user.getUpdatedAt());

        return userPersistence;
    }

    public static User persistenceToDomain(UserPersistence persistence) {
        final User domain = new User();
        domain.setUserId(persistence.getUserId());
        domain.setEmail(persistence.getEmail());
        domain.setPassword(persistence.getPassword());
        domain.setNickname(persistence.getNickname());
        domain.setCreatedAt(persistence.getCreatedAt());
        domain.setUpdatedAt(persistence.getUpdatedAt());

        return domain;
    }
}
