package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokensTest {

    @Test
    void givenValidValues_whenCreating_thenCreateTokens() {
        // Given
        AccessToken accessToken = new AccessToken("AccessToken");
        RefreshToken refreshToken = new RefreshToken("RefreshToken");

        // When
        Tokens tokens = new Tokens(accessToken, refreshToken);

        // Then
        assertAll(
            () -> assertInstanceOf(Tokens.class, tokens),
            () -> assertEquals(accessToken, tokens.getAccessToken()),
            () -> assertEquals(refreshToken, tokens.getRefreshToken())
        );
    }

}
