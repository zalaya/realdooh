package com.econocom.realdooh.infrastructure.persistence;

import com.econocom.realdooh.domain.User;
import com.econocom.realdooh.domain.vo.Email;
import com.econocom.realdooh.domain.vo.HashedPassword;
import com.econocom.realdooh.infrastructure.persistence.user.UserEntity;

import com.econocom.realdooh.infrastructure.persistence.user.UserEntityMapper;
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
        String hashedPassword = "HashedPassword";
        UserEntity entity = new UserEntity(id, email, hashedPassword);

        // When
        User domain = mapper.toDomain(entity);

        // Then
        assertAll(
            () -> assertInstanceOf(User.class, domain),
            () -> assertEquals(email, domain.getEmail().getValue()),
            () -> assertEquals(hashedPassword, domain.getHashedPassword().getValue())
        );
    }

    @Test
    void givenUser_whenToEntity_thenReturnUserEntity() {
        // Given
        Email email = new Email("Email");
        HashedPassword hashedPassword = new HashedPassword("HashedPassword");
        User domain = new User(email, hashedPassword);

        // When
        UserEntity entity = mapper.toEntity(domain);

        // Then
        assertAll(
            () -> assertInstanceOf(UserEntity.class, entity),
            () -> assertNull(entity.getId()),
            () -> assertEquals(email.getValue(), entity.getEmail()),
            () -> assertEquals(hashedPassword.getValue(), entity.getHashedPassword())
        );
    }

}
