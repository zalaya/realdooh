package com.econocom.realdooh.infrastructure.security.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptPasswordHasherTest {

    private BCryptPasswordHasher hasher;

    @BeforeEach
    void beforeEach() {
        hasher = new BCryptPasswordHasher(new BCryptPasswordEncoder());
    }

    @Test
    void givenRawPassword_whenHashed_thenMatches() {
        // Given
        String rawPassword = "Password";

        // When
        String hashedPassword = hasher.hash(rawPassword);

        // Then
        assertTrue(hasher.matches(rawPassword, hashedPassword));
    }

    @Test
    void givenWrongPassword_whenMatches_thenReturnFalse() {
        // Given
        String rawPassword = "Password 1";
        String wrongPassword = "Password 2";

        // When
        String hashedPassword = hasher.hash(rawPassword);

        // Then
        assertFalse(hasher.matches(wrongPassword, hashedPassword));
    }

}
