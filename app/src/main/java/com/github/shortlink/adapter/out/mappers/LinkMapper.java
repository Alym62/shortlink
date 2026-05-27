package com.github.shortlink.adapter.out.mappers;

import com.github.shortlink.adapter.in.dto.CreateLinkRequestDto;
import com.github.shortlink.adapter.in.dto.CreateLinkResponseDto;
import com.github.shortlink.adapter.out.persistence.LinkPersistence;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.domain.vo.UtmTags;

import java.util.UUID;

public final class LinkMapper {
    private LinkMapper() {
    }

    public static Link dtoRequestToDomain(CreateLinkRequestDto requestDto, UUID userId) {
        final Link domain = new Link(requestDto.linkSlug(), requestDto.urlOriginal(), requestDto.expirationDate());
        domain.setUtmTags(requestDto.utm());
        domain.setUserId(userId);

        return domain;
    }

    public static CreateLinkResponseDto dtoResponseFromDomain(String urlShort) {
        // @TODO: Ajustar dominio
        return new CreateLinkResponseDto("http://localhost:3000/%s".formatted(urlShort));
    }

    public static LinkPersistence persistenceFromDomain(Link link) {
        final LinkPersistence linkPersistence = new LinkPersistence();
        linkPersistence.setLinkId(link.getLinkId());
        linkPersistence.setUrlOriginal(link.getUrlOriginal());
        linkPersistence.setUtmSource(link.getUtmTags().utmSource());
        linkPersistence.setUtmCampaign(link.getUtmTags().utmCampaign());
        linkPersistence.setUtmMedium(link.getUtmTags().utmMedium());
        linkPersistence.setUtmContent(link.getUtmTags().utmContent());
        linkPersistence.setUserId(link.getUserId());
        linkPersistence.setExpirationDateTime(link.getExpirationDateTime());
        linkPersistence.setCreatedAt(link.getCreatedAt());
        linkPersistence.setUpdatedAt(link.getUpdatedAt());

        return linkPersistence;
    }

    public static Link persistenceToDomain(LinkPersistence persistence) {
        final UtmTags utmTags = new UtmTags(persistence.getUtmSource(), persistence.getUtmMedium(),
                persistence.getUtmCampaign(), persistence.getUtmContent());
        final Link domain = new Link();
        domain.setLinkId(persistence.getLinkId());
        domain.setUrlOriginal(persistence.getUrlOriginal());
        domain.setUtmTags(utmTags);
        domain.setUserId(persistence.getUserId());
        domain.setExpirationDateTime(persistence.getExpirationDateTime());
        domain.setCreatedAt(persistence.getCreatedAt());
        domain.setUpdatedAt(persistence.getUpdatedAt());

        return domain;
    }
}
