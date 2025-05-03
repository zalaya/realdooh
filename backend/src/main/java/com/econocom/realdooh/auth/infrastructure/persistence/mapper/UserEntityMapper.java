package com.econocom.realdooh.auth.infrastructure.persistence.mapper;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    @Mapping(target = "id", expression = "java(new UserId(entity.getId()))")
    @Mapping(target = "email", expression = "java(new Email(entity.getEmail()))")
    @Mapping(target = "password", expression = "java(new HashedPassword(entity.getPassword()))")
    User toDomain(UserEntity entity);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "password", source = "password.value")
    UserEntity toEntity(User domain);
}
