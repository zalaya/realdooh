package com.econocom.realdooh.auth.domain.vo.token;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenTest {

    @Test
    void givenValidValue_whenCreating_thenCreateEmail() {
        // Given
        String value = "RefreshToken";

        // When
        RefreshToken refreshToken = new RefreshToken(value);

        // Then
        assertAll(
            () -> assertInstanceOf(RefreshToken.class, refreshToken),
            () -> assertEquals(value, refreshToken.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(InvalidValueObjectException.class, () -> new RefreshToken(value));
    }

}
