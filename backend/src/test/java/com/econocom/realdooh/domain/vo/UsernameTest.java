package com.econocom.realdooh.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {

    @Test
    void givenValidValue_whenCreating_thenCreateUsername() {
        // Given
        String value = "Username";

        // When
        Username username = new Username(value);

        // Then
        assertAll(
            () -> assertInstanceOf(Username.class, username),
            () -> assertEquals(value, username.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> new Username(value));
    }

}
