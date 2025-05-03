package com.econocom.realdooh.auth.domain.vo.user;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void givenValidValue_whenCreating_thenCreateEmail() {
        // Given
        String value = "Email";

        // When
        Email email = new Email(value);

        // Then
        assertAll(
            () -> assertInstanceOf(Email.class, email),
            () -> assertEquals(value, email.getValue())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidValue_whenCreating_thenThrowException(String value) {
        // When / Then
        assertThrows(InvalidValueObjectException.class, () -> new Email(value));
    }

}
