package com.econocom.realdooh.auth.infrastructure.security.rest;

import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.Password;
import com.econocom.realdooh.auth.infrastructure.security.hash.BCryptPasswordHasher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordHasherTest {

    private BCryptPasswordHasher hasher;

    @BeforeEach
    void beforeEach() {
        hasher = new BCryptPasswordHasher(new BCryptPasswordEncoder());
    }

    @Test
    void givenValidPassword_whenHash_thenMatchesWithOriginal() {
        // Given
        Password password = new Password("Password");

        // When
        HashedPassword hashed = hasher.hash(password);

        // Then
        assertAll(
            () -> assertNotNull(hashed),
            () -> assertTrue(hasher.matches(password, hashed))
        );
    }

    @Test
    void givenInvalidPassword_whenMatches_thenReturnFalse() {
        // Given
        Password original = new Password("Password 1");
        Password wrong = new Password("Password 2");

        HashedPassword hashed = hasher.hash(original);

        // Then
        assertFalse(hasher.matches(wrong, hashed));
    }

}
