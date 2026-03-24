package com.github.shortlink.core.usecases;

import com.github.shortlink.core.domain.User;
import com.github.shortlink.adapter.in.exceptions.UserAlreadyExistsException;
import com.github.shortlink.core.port.in.CreateUserPortIn;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateUserUseCase implements CreateUserPortIn {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);

    private final UserRepositoryOut userRepositoryOut;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepositoryOut userRepositoryOut, BCryptPasswordEncoder passwordEncoder) {
        this.userRepositoryOut = userRepositoryOut;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User user) {
        logger.info("Creating user {}", user.getEmail());

        final Optional<User> userExistsWithEmail = userRepositoryOut.findByEmail(user.getEmail());
        if (userExistsWithEmail.isPresent()) {
            throw new UserAlreadyExistsException("Não foi possível salvar um usuário com esse endereço de e-mail.");
        }

        encodePassword(user, user.getPassword());
        final User userCreated = userRepositoryOut.save(user);

        logger.info("User created {}", userCreated.getUserId());

        return userCreated;
    }

    private void encodePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
    }
}
