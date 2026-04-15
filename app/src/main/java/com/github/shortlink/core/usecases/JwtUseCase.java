package com.github.shortlink.core.usecases;

import com.github.shortlink.core.port.in.JwtPortIn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class JwtUseCase implements JwtPortIn {
    @Value("${jwt.expires.in}")
    private Long expiresIn;

    private final JwtEncoder jwtEncoder;

    public JwtUseCase(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String execute(UUID userId, String email) {
        final JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(userId.toString())
                .issuer("short-link")
                .claim("email", email)
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();
    }
}
