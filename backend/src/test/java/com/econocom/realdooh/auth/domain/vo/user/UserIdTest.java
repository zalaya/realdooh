package com.econocom.realdooh.auth.domain.vo.user;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    void givenValidValue_whenCreating_thenCreateUserId() {
        // Given
        Long value = 1L;

        // When
        UserId userId = new UserId(value);

        // Then
        assertAll(
            () -> assertInstanceOf(UserId.class, userId),
            () -> assertEquals(value, userId.getValue())
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {0L, -1L, -100L})
    void givenInvalidValue_whenCreating_thenThrowException(Long value) {
        // When / Then
        assertThrows(InvalidValueObjectException.class, () -> new UserId(value));
    }

}
