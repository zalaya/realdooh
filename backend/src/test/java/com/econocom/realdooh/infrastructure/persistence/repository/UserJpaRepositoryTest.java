package com.econocom.realdooh.infrastructure.persistence.repository;

import com.econocom.realdooh.infrastructure.persistence.entity.UserEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository repository;

    @Test
    @Sql(statements = "INSERT INTO users (email, hashed_password) VALUES ('Email', 'HashedPassword')")
    void givenExistingEmail_whenFindByEmail_thenReturnUserEntity() {
        // When
        Optional<UserEntity> entity = repository.findByEmail("Email");

        // Then
        assertAll(
            () -> assertEquals("Email", entity.get().getEmail()),
            () -> assertEquals("HashedPassword", entity.get().getHashedPassword())
        );
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenReturnEmpty() {
        // When
        Optional<UserEntity> entity = repository.findByEmail("Email");

        // Then
        assertEquals(Optional.empty(), entity);
    }

}
