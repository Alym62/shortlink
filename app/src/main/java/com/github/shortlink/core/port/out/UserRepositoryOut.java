package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.User;

public interface UserRepositoryOut {
    User save(User user);
}
