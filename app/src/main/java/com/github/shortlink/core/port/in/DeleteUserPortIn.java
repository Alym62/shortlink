package com.github.shortlink.core.port.in;

import java.util.UUID;

public interface DeleteUserPortIn {
    void execute(UUID userId);
}
