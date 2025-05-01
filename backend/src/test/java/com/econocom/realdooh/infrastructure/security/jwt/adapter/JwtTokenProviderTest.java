package com.econocom.realdooh.infrastructure.security.jwt.adapter;

import com.econocom.realdooh.infrastructure.security.jwt.configuration.JwtProperties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider provider;

    @BeforeEach
    void beforeEach() {
        JwtProperties properties = new JwtProperties();
        properties.setSecret("this-is-a-secret");
        properties.setExpiration(3600);

        provider = new JwtTokenProvider(properties);
    }

    @Test
    void givenValidClaims_whenGenerate_thenReturnValidToken() {
        // Given
        Map<String, Object> claims = Map.of("key1", "value1", "key2", "value2");

        // When
        String token = provider.generate(claims);

        // Then
        DecodedJWT decoded = JWT.decode(token);
        assertAll(
            () -> assertTrue(provider.validate(token)),
            () -> assertEquals("value1", decoded.getClaim("key1").asString()),
            () -> assertEquals("value2", decoded.getClaim("key2").asString())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"this-is-an-invalid-token", "abc-manually-generated-xyz"})
    void givenInvalidToken_whenValidate_thenReturnFalse(String token) {
        // When / Then
        assertFalse(provider.validate(token));
    }

    @Test
    void givenManuallyModifiedToken_whenValidate_thenReturnFalse() {
        // Given
        Map<String, Object> claims = Map.of("key1", "value1", "key2", "value2");
        String token = provider.generate(claims);
        token = token + "this-is-a-modification";

        // When / Then
        assertFalse(provider.validate(token));
    }

}
