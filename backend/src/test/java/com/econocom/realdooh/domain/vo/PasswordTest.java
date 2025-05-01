package com.econocom.realdooh.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void givenValidValue_whenCreating_thenCreatePassword() {
        // Given
        String value = "Password";

        // When
        Password password = new Password(value);

        // Then
        assertAll(
            () -> assertInstanceOf(Password.class, password),
            () -> assertEquals(value, password.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> new Password(value));
    }

}
