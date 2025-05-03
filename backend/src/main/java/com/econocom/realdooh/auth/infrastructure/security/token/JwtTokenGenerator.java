package com.econocom.realdooh.auth.infrastructure.security.token;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.outbound.security.token.TokenGenerator;
import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;
import com.econocom.realdooh.auth.infrastructure.security.configuration.properties.TokenProperties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator {

    private final TokenProperties properties;

    @Override
    public AccessToken generateAccessToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + properties.getAccessTokenExpiration() * 1000L);
        String token = JWT.create()
            .withSubject(user.getEmail().getValue())
            .withIssuer(properties.getIssuer())
            .withIssuedAt(now)
            .withExpiresAt(expiration)
            .sign(getAlgorithm());

        return new AccessToken(token);
    }

    @Override
    public RefreshToken generateRefreshToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + properties.getRefreshTokenExpiration() * 1000L);
        String token = JWT.create()
            .withSubject(user.getEmail().getValue())
            .withIssuer(properties.getIssuer())
            .withIssuedAt(now)
            .withExpiresAt(expiration)
            .sign(getAlgorithm());

        return new RefreshToken(token);
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(properties.getSecret());
    }

}
