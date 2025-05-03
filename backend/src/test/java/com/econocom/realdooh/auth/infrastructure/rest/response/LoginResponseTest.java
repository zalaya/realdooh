package com.econocom.realdooh.auth.infrastructure.rest.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void givenValidValues_whenCreating_thenCreateLoginResponse() {
        // Given
        String accessToken = "AccessTokenValue";
        String refreshToken = "RefreshTokenValue";
        long expiresIn = 3600;

        // When
        LoginResponse response = new LoginResponse(accessToken, refreshToken, expiresIn);

        // Then
        assertAll(
            () -> assertInstanceOf(LoginResponse.class, response),
            () -> assertEquals("Bearer", response.getTokenType()),
            () -> assertEquals(accessToken, response.getAccessToken()),
            () -> assertEquals(refreshToken, response.getRefreshToken()),
            () -> assertEquals(expiresIn, response.getExpiresIn())
        );
    }

}
