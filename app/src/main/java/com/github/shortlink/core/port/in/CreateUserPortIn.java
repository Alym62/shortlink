package com.github.shortlink.core.port.in;

import com.github.shortlink.adapter.in.dto.CreateUserRequestDto;
import com.github.shortlink.core.domain.User;

public interface CreateUserPortIn {
    User execute(User user);
}
