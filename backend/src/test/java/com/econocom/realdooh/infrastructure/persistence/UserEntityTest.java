package com.econocom.realdooh.infrastructure.persistence;

import com.econocom.realdooh.infrastructure.persistence.user.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

    @Test
    void givenValidValues_whenCreate_thenReturnUserEntity() {
        // Given
        Long id = 1L;
        String email = "Email";
        String hashedPassword = "HashedPassword";

        // When
        UserEntity entity = new UserEntity(id, email, hashedPassword);

        // Then
        assertAll(
            () -> assertEquals(id, entity.getId()),
            () -> assertEquals(email, entity.getEmail()),
            () -> assertEquals(hashedPassword, entity.getHashedPassword())
        );
    }

}
