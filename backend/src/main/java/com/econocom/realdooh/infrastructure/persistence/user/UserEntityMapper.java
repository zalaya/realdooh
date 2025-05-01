package com.econocom.realdooh.infrastructure.persistence.user;

import com.econocom.realdooh.domain.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    @Mapping(target = "email", expression = "java(new Email(entity.getEmail()))")
    @Mapping(target = "hashedPassword", expression = "java(new HashedPassword(entity.getHashedPassword()))")
    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "hashedPassword", source = "hashedPassword.value")
    UserEntity toEntity(User domain);

}
