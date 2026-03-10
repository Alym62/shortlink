package com.github.shortlink.core.usecases;

import com.github.shortlink.core.domain.User;
import com.github.shortlink.core.port.in.CreateUserPortIn;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase implements CreateUserPortIn {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);

    private final UserRepositoryOut userRepositoryOut;

    public CreateUserUseCase(UserRepositoryOut userRepositoryOut) {
        this.userRepositoryOut = userRepositoryOut;
    }

    @Override
    public User execute(User user) {
        logger.info("Creating user {}", user.getEmail());
        final User userCreated = userRepositoryOut.save(user);
        logger.info("User created {}", userCreated.getUserId());

        return userCreated;
    }
}
