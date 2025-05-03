package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.Password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void givenValidValues_whenCreating_thenCreateCredentials() {
        // Given
        Email email = new Email("Email");
        Password password = new Password("HashedPassword");

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
