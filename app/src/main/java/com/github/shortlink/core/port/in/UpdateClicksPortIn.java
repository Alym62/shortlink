package com.github.shortlink.core.port.in;

import com.github.shortlink.core.domain.Link;

public interface UpdateClicksPortIn {
    void execute(Link link);
}
