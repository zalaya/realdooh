package com.econocom.realdooh.domain.model;

import com.econocom.realdooh.domain.vo.Password;
import com.econocom.realdooh.domain.vo.Email;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void givenValidValues_whenCreating_thenCreateCredentials() {
        // Given
        Email email = new Email("Username");
        Password password = new Password("Password");

        // When
        Credentials credentials = new Credentials(email, password);

        // Then
        assertAll(
            () -> assertInstanceOf(Credentials.class, credentials),
            () -> assertEquals(email, credentials.getEmail()),
            () -> assertEquals(password, credentials.getPassword())
        );
    }

}
