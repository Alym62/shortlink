package com.github.shortlink.core.port.in;

import java.util.UUID;

public interface JwtPortIn {
    String execute(UUID userId, String email);
}
