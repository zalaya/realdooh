package com.econocom.realdooh.auth.infrastructure.persistence.repository;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;
import com.econocom.realdooh.auth.infrastructure.persistence.mapper.UserEntityMapper;

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
        UserId id = new UserId(1L);
        Email email = new Email("Email");
        HashedPassword password = new HashedPassword("HashedPassword");

        when(repository.findByEmail("Email")).thenReturn(Optional.of(new UserEntity(1L, "Email", "HashedPassword")));
        when(mapper.toDomain(new UserEntity(1L, "Email", "HashedPassword"))).thenReturn(new User(id, email, password));

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
        Optional<User> domain = adapter.findByEmail(new Email("Email"));

        // Then
        assertEquals(Optional.empty(), domain);
    }

}
