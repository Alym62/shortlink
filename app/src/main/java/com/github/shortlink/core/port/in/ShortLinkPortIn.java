package com.github.shortlink.core.port.in;

import com.github.shortlink.core.domain.Link;

public interface ShortLinkPortIn {
    Link execute(Link link);
}
