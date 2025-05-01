package com.econocom.realdooh.infrastructure.security.jwt;

import com.econocom.realdooh.domain.port.outbound.security.TokenProvider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final JwtProperties properties;
    private final Algorithm algorithm;

    public JwtTokenProvider(JwtProperties properties) {
        this.properties = properties;
        this.algorithm = Algorithm.HMAC256(properties.getSecret());
    }

    @Override
    public String generate(Map<String, Object> claims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + properties.getExpiration() * 1000L);

        return JWT.create()
            .withIssuedAt(now)
            .withExpiresAt(expiration)
            .withPayload(claims)
            .sign(algorithm);
    }

    @Override
    public boolean validate(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

}
