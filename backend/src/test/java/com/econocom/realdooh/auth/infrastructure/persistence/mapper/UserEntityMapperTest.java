package com.econocom.realdooh.auth.infrastructure.persistence.mapper;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {

    private final UserEntityMapper mapper = Mappers.getMapper(UserEntityMapper.class);

    @Test
    void givenUserEntity_whenToDomain_thenReturnUser() {
        // Given
        Long id = 1L;
        String email = "Email";
        String password = "HashedPassword";
        UserEntity entity = new UserEntity(id, email, password);

        // When
        User domain = mapper.toDomain(entity);

        // Then
        assertAll(
            () -> assertInstanceOf(User.class, domain),
            () -> assertEquals(id, domain.getId().getValue()),
            () -> assertEquals(email, domain.getEmail().getValue()),
            () -> assertEquals(password, domain.getPassword().getValue())
        );
    }

    @Test
    void givenUser_whenToEntity_thenReturnUserEntity() {
        // Given
        UserId id = new UserId(1L);
        Email email = new Email("Email");
        HashedPassword password = new HashedPassword("HashedPassword");
        User domain = new User(id, email, password);

        // When
        UserEntity entity = mapper.toEntity(domain);

        // Then
        assertAll(
            () -> assertInstanceOf(UserEntity.class, entity),
            () -> assertEquals(id.getValue(), entity.getId()),
            () -> assertEquals(email.getValue(), entity.getEmail()),
            () -> assertEquals(password.getValue(), entity.getPassword())
        );
    }

}
