package com.econocom.realdooh.auth.infrastructure.security.token;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.security.configuration.properties.TokenProperties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenGeneratorTest {

    private JwtTokenGenerator generator;

    @BeforeEach
    void beforeEach() {
        TokenProperties properties = new TokenProperties();

        properties.setSecret("this-is-a-secret");
        properties.setIssuer("realdooh-auth");
        properties.setAccessTokenExpiration(3600);
        properties.setRefreshTokenExpiration(86400);

        generator = new JwtTokenGenerator(properties);
    }

    @Test
    void givenValidUser_whenGenerateAccessToken_thenReturnValidToken() {
        // Given
        User user = new User(
            new UserId(1L),
            new Email("Email"),
            new HashedPassword("HashedPassword")
        );

        // When
        AccessToken accessToken = generator.generateAccessToken(user);

        // Then
        DecodedJWT decoded = JWT.decode(accessToken.getValue());

        assertAll(
            () -> assertNotNull(accessToken),
            () -> assertEquals("Email", decoded.getSubject()),
            () -> assertEquals("realdooh-auth", decoded.getIssuer()),
            () -> assertTrue(decoded.getExpiresAt().getTime() > System.currentTimeMillis())
        );
    }

    @Test
    void givenValidUser_whenGenerateRefreshToken_thenReturnValidToken() {
        // Given
        User user = new User(
            new UserId(1L),
            new Email("Email"),
            new HashedPassword("HashedPassword")
        );

        // When
        RefreshToken refreshToken = generator.generateRefreshToken(user);

        // Then
        DecodedJWT decoded = JWT.decode(refreshToken.getValue());

        assertAll(
            () -> assertNotNull(refreshToken),
            () -> assertEquals("Email", decoded.getSubject()),
            () -> assertEquals("realdooh-auth", decoded.getIssuer()),
            () -> assertTrue(decoded.getExpiresAt().getTime() > System.currentTimeMillis())
        );
    }

}
