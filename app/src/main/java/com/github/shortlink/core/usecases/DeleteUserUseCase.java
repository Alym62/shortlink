package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.exceptions.UserNotFoundException;
import com.github.shortlink.core.port.in.DeleteUserPortIn;
import com.github.shortlink.core.port.out.UserRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserUseCase implements DeleteUserPortIn {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserUseCase.class);

    private final UserRepositoryOut userRepositoryOut;

    public DeleteUserUseCase(UserRepositoryOut userRepositoryOut) {
        this.userRepositoryOut = userRepositoryOut;
    }

    @Override
    public void execute(UUID userId) {
        logger.info("Deleting user {}", userId);
        final boolean existsUser = userRepositoryOut.existsUserById(userId);
        if (!existsUser) {
            throw new UserNotFoundException("Usuário não encontrado em nossa base de dados.");
        }

        userRepositoryOut.deleteById(userId);
    }
}
