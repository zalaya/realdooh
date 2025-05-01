package com.econocom.realdooh.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class HashedPasswordTest {

    @Test
    void givenValidValue_whenCreating_thenCreateHashedPassword() {
        // Given
        String value = "HashedPassword";

        // When
        HashedPassword hashedPassword = new HashedPassword(value);

        // Then
        assertAll(
            () -> assertInstanceOf(HashedPassword.class, hashedPassword),
            () -> assertEquals(value, hashedPassword.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> new HashedPassword(value));
    }

}
