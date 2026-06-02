package com.github.shortlink.adapter.in;

import com.github.shortlink.adapter.in.dto.CreateLinkRequestDto;
import com.github.shortlink.adapter.in.dto.CreateLinkResponseDto;
import com.github.shortlink.adapter.out.mappers.LinkMapper;
import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.port.in.RedirectLinkPortIn;
import com.github.shortlink.core.port.in.ShortLinkPortIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    @Value("${url.dominio}")
    private String urlDominio;

    private final ShortLinkPortIn shortLinkPortIn;
    private final RedirectLinkPortIn redirectLinkPortIn;

    public LinkController(ShortLinkPortIn shortLinkPortIn, RedirectLinkPortIn redirectLinkPortIn) {
        this.shortLinkPortIn = shortLinkPortIn;
        this.redirectLinkPortIn = redirectLinkPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateLinkResponseDto> shortLink(@RequestBody CreateLinkRequestDto dto,
                                                           JwtAuthenticationToken token) {
        final String userId = token.getTokenAttributes().get("sub").toString();
        final Link link = shortLinkPortIn.execute(LinkMapper.dtoRequestToDomain(dto, UUID.fromString(userId)));

        final CreateLinkResponseDto body = LinkMapper.dtoResponseFromDomain(urlDominio + link.getLinkId());

        return ResponseEntity.created(URI.create("/")).body(body);
    }

    @GetMapping("{linkId}")
    public ResponseEntity<CreateLinkResponseDto> redirectByLinkId(@PathVariable String linkId) {
        final String fullUrl = redirectLinkPortIn.execute(linkId);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(fullUrl));

        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
    }
}
