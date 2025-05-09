package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;
import com.econocom.realdooh.auth.infrastructure.persistence.mapper.UserEntityMapper;
import com.econocom.realdooh.shared.domain.exception.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserRepositoryAdapterTest {

    private UserJpaRepository repository;
    private UserRepositoryAdapter adapter;
    private UserEntityMapper mapper;

    @BeforeEach
    void beforeEach() {
        repository = mock(UserJpaRepository.class);
        mapper = mock(UserEntityMapper.class);
        adapter = new UserRepositoryAdapter(repository, mapper);
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnUser() {
        // Given
        UserEntity entity = new UserEntity(1L, "Email", "HashedPassword");
        when(repository.findByEmail("Email")).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(new User(new UserId(1L), new Email("Email"), new HashedPassword("HashedPassword")));

        // When
        User domain = adapter.findByEmail(new Email("Email"));

        // Then
        assertEquals(1L, domain.getId().getValue());
        assertEquals("Email", domain.getEmail().getValue());
        assertEquals("HashedPassword", domain.getPassword().getValue());
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenReturnEmpty() {
        // Given
        when(repository.findByEmail("Email")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(EntityNotFoundException.class, () -> adapter.findByEmail(new Email("Email")));
        verify(repository).findByEmail("Email");
        verifyNoInteractions(mapper);
    }

}
