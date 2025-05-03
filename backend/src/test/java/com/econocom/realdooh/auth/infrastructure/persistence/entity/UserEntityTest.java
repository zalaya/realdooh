package com.econocom.realdooh.auth.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

    @Test
    void givenValidValues_whenCreate_thenReturnUserEntity() {
        // Given
        Long id = 1L;
        String email = "Email";
        String password = "HashedPassword";

        // When
        UserEntity entity = new UserEntity(id, email, password);

        // Then
        assertAll(
            () -> assertEquals(id, entity.getId()),
            () -> assertEquals(email, entity.getEmail()),
            () -> assertEquals(password, entity.getPassword())
        );
    }

}
