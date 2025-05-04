package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;

import com.econocom.realdooh.shared.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepositoryAdapterTest {

    private UserJpaRepository repository;
    private UserRepositoryAdapter adapter;

    @BeforeEach
    void beforeEach() {
        repository = mock(UserJpaRepository.class);
        adapter = new UserRepositoryAdapter(repository);
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnUser() {
        // Given
        when(repository.findByEmail("Email")).thenReturn(Optional.of(new UserEntity(1L, "Email", "HashedPassword")));

        // When
        User domain = adapter.findByEmail(new Email("Email")).orElseThrow();

        // Then
        assertEquals(1L, domain.getId().getValue());
        assertEquals("Email", domain.getEmail().getValue());
        assertEquals("HashedPassword", domain.getPassword().getValue());
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenReturnEmpty() {
        // Given
        when(repository.findByEmail("Email")).thenReturn(Optional.empty());

        // When
        Executable executable = () -> adapter.findByEmail(new Email("Email"));

        // Then
        assertThrows(EntityNotFoundException.class, executable);
    }

}
