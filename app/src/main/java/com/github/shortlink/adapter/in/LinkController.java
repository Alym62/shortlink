package com.github.shortlink.adapter.in;

import com.github.shortlink.adapter.in.dto.CreateLinkRequestDto;
import com.github.shortlink.adapter.in.dto.CreateLinkResponseDto;
import com.github.shortlink.adapter.out.mappers.LinkMapper;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.port.in.ShortLinkPortIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/links")
public class LinkController {
    @Value("${url.dominio}")
    private String urlDominio;

    private final ShortLinkPortIn shortLinkPortIn;

    public LinkController(ShortLinkPortIn shortLinkPortIn) {
        this.shortLinkPortIn = shortLinkPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateLinkResponseDto> shortLink(@RequestBody CreateLinkRequestDto dto,
                                                           JwtAuthenticationToken token) {
        final String userId = token.getTokenAttributes().get("sub").toString();
        final Link link = shortLinkPortIn.execute(LinkMapper.dtoRequestToDomain(dto, UUID.fromString(userId)));

        final CreateLinkResponseDto body = LinkMapper.dtoResponseFromDomain(urlDominio + link.getLinkId());

        return ResponseEntity.created(URI.create("/")).body(body);
    }
}
