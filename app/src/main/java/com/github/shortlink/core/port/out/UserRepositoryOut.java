package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryOut {
    User save(User user);

    Optional<User> findByEmail(String email);

    void deleteById(UUID userId);

    boolean existsUserById(UUID userId);
}
