package com.econocom.realdooh.infrastructure.persistence.adapter;

import com.econocom.realdooh.domain.model.User;
import com.econocom.realdooh.domain.vo.Email;
import com.econocom.realdooh.domain.vo.HashedPassword;
import com.econocom.realdooh.infrastructure.persistence.entity.UserEntity;
import com.econocom.realdooh.infrastructure.persistence.mapper.UserEntityMapper;
import com.econocom.realdooh.infrastructure.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepositoryAdapterTest {

    private UserJpaRepository repository;
    private UserEntityMapper mapper;
    private UserRepositoryAdapter adapter;

    @BeforeEach
    void beforeEach() {
        repository = mock(UserJpaRepository.class);
        mapper = mock(UserEntityMapper.class);
        adapter = new UserRepositoryAdapter(repository, mapper);
    }

    @Test
    void givenExistingEmail_whenFindByEmail_thenReturnUser() {
        // Given
        when(repository.findByEmail("Email")).thenReturn(Optional.of(new UserEntity(1L, "Email", "HashedPassword")));
        when(mapper.toDomain(new UserEntity(1L, "Email", "HashedPassword"))).thenReturn(new User(new Email("Email"), new HashedPassword("HashedPassword")));

        // When
        User domain = adapter.findByEmail(new Email("Email")).orElseThrow();

        // Then
        assertEquals("Email", domain.getEmail().getValue());
        assertEquals("HashedPassword", domain.getHashedPassword().getValue());
    }

    @Test
    void givenNonExistingEmail_whenFindByEmail_thenReturnEmpty() {
        // Given
        when(repository.findByEmail("Email")).thenReturn(Optional.empty());

        // When
        Optional<User> domain = adapter.findByEmail(new Email("Email"));

        // Then
        assertEquals(Optional.empty(), domain);
    }

}
