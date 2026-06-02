package com.github.shortlink.core.port.out;

import com.github.shortlink.core.domain.Link;

import java.util.Optional;

public interface LinkRepositoryOut {
    Link save(Link link);

    boolean existsLinkBySlug(String slug);

    Optional<Link> findById(String slug);
}
