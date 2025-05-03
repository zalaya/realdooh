package com.econocom.realdooh.auth.domain.vo.token;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenTest {

    @Test
    void givenValidValue_whenCreating_thenCreateAccessToken() {
        // Given
        String value = "AccessToken";

        // When
        AccessToken accessToken = new AccessToken(value);

        // Then
        assertAll(
            () -> assertInstanceOf(AccessToken.class, accessToken),
            () -> assertEquals(value, accessToken.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(InvalidValueObjectException.class, () -> new AccessToken(value));
    }

}
