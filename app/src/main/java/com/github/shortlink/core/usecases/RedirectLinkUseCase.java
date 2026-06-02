package com.github.shortlink.core.usecases;

import com.github.shortlink.adapter.in.exceptions.LinkNotFoundException;
import com.github.shortlink.core.commons.Constants;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.vo.UtmTags;
import com.github.shortlink.core.port.in.RedirectLinkPortIn;
import com.github.shortlink.core.port.out.LinkRepositoryOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RedirectLinkUseCase implements RedirectLinkPortIn {
    private static final Logger logger = LoggerFactory.getLogger(RedirectLinkUseCase.class);

    private final LinkRepositoryOut linkRepositoryOut;

    public RedirectLinkUseCase(LinkRepositoryOut linkRepositoryOut) {
        this.linkRepositoryOut = linkRepositoryOut;
    }

    @Override
    public String execute(String linkId) {
        logger.info("Link id received -> {}", linkId);

        final Link linkForRedirect = linkRepositoryOut.findById(linkId)
                .orElseThrow(() -> new LinkNotFoundException("Não foi possível acessar esse link. Tente novamente mais tarde."));

        // @TODO: publicar no SQS (AWS) para fazer a parte de analiticos
        return generateFullUrlWithParameters(linkForRedirect);
    }

    private String generateFullUrlWithParameters(Link link) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(link.getUrlOriginal());

        final UtmTags utmTags = link.getUtmTags();
        if (StringUtils.hasText(utmTags.utmCampaign())) {
            uriComponentsBuilder.queryParam(Constants.UTM_CAMPAIGN, utmTags.utmCampaign());
        }
        if (StringUtils.hasText(link.getUtmTags().utmSource())) {
            uriComponentsBuilder.queryParam(Constants.UTM_SOURCE, utmTags.utmSource());
        }
        if (StringUtils.hasText(link.getUtmTags().utmMedium())) {
            uriComponentsBuilder.queryParam(Constants.UTM_MEDIUM, utmTags.utmMedium());
        }
        if (StringUtils.hasText(link.getUtmTags().utmContent())) {
            uriComponentsBuilder.queryParam(Constants.UTM_CONTENT, utmTags.utmContent());
        }

        return uriComponentsBuilder.toUriString();
    }
}
