package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.exceptions.LinkAlreadyExistsException;
import com.github.shortlink.adapter.in.exceptions.ShortLinkBusinessException;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.port.in.ShortLinkPortIn;
import com.github.shortlink.core.port.out.LinkRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShortLinkUseCase implements ShortLinkPortIn {
    private static final Logger logger = LoggerFactory.getLogger(ShortLinkUseCase.class);

    private final LinkRepositoryOut linkRepositoryOut;

    public ShortLinkUseCase(LinkRepositoryOut linkRepositoryOut) {
        this.linkRepositoryOut = linkRepositoryOut;
    }

    @Override
    public Link execute(Link link) {
        logger.info("Shorten link -> {} - slug -> {}", link.getUrlOriginal(), link.getLinkId());

        if (link.getExpirationDateTime().isBefore(LocalDateTime.now())) {
            throw new ShortLinkBusinessException("Não é possível utilizar uma data de expiração do link antes da data atual.");
        }

        final boolean linkExists = linkRepositoryOut.existsLinkBySlug(link.getLinkId());
        if (linkExists) {
            throw new LinkAlreadyExistsException("Não foi possível encurtar o link com esse slug");
        }

        final Link linkCreated = linkRepositoryOut.save(link);

        logger.info("Link short created with user -> {}", linkCreated.getUserId());

        return linkCreated;
    }
}
