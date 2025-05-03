package com.econocom.realdooh.auth.infrastructure.rest.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void givenValidRequest_whenValidated_thenNoViolations() {
        // Given
        LoginRequest request = new LoginRequest();
        request.setEmail("user@example.com");
        request.setPassword("Password");

        // When
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        // Then
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "invalid-email", "no-at-symbol.com"})
    void givenInvalidEmail_whenValidated_thenDetectViolation(String email) {
        // Give
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword("ValidPassword");

        // When
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(validation -> validation.getPropertyPath().toString().equals("email")));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void givenInvalidPassword_whenValidated_thenDetectViolation(String password) {
        // Given
        LoginRequest request = new LoginRequest();
        request.setEmail("user@example.com");
        request.setPassword(password);

        // When
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(validation -> validation.getPropertyPath().toString().equals("password")));
    }

}
