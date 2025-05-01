package com.econocom.realdooh.domain;

import com.econocom.realdooh.domain.vo.Email;
import com.econocom.realdooh.domain.vo.HashedPassword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void givenValidValues_whenCreating_thenCreateUser() {
        // Given
        Email email = new Email("Username");
        HashedPassword hashedPassword = new HashedPassword("HashedPassword");

        // When
        User user = new User(email, hashedPassword);

        // Then
        assertAll(
            () -> assertInstanceOf(User.class, user),
            () -> assertEquals(email, user.getEmail()),
            () -> assertEquals(hashedPassword, user.getHashedPassword())
        );
    }

}
