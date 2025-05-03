package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void givenValidValues_whenCreating_thenCreateUser() {
        // Given
        UserId id = new UserId(1L);
        Email email = new Email("Username");
        HashedPassword password = new HashedPassword("HashedPassword");

        // When
        User user = new User(id, email, password);

        // Then
        assertAll(
            () -> assertInstanceOf(User.class, user),
            () -> assertEquals(id, user.getId()),
            () -> assertEquals(email, user.getEmail()),
            () -> assertEquals(password, user.getPassword())
        );
    }

}
