package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.Link;

public interface LinkRepositoryOut {
    Link save(Link link);

    boolean existsLinkBySlug(String slug);
}
