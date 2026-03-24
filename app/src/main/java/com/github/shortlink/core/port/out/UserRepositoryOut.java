package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.User;

import java.util.Optional;

public interface UserRepositoryOut {
    User save(User user);

    Optional<User> findByEmail(String email);
}
