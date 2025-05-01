package com.econocom.realdooh.domain.model;

import com.econocom.realdooh.domain.vo.Password;
import com.econocom.realdooh.domain.vo.Username;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void givenValidValues_whenCreating_thenCreateCredentials() {
        // Given
        Username username = new Username("Username");
        Password password = new Password("Password");

        // When
        Credentials credentials = new Credentials(username, password);

        // Then
        assertAll(
            () -> assertInstanceOf(Password.class, password),
            () -> assertEquals(username, credentials.getUsername()),
            () -> assertEquals(password, credentials.getPassword())
        );
    }

}
