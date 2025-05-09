package com.econocom.realdooh.auth.infrastructure.persistence.mapper;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.infrastructure.persistence.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "password", target = "password.value")
    User toDomain(UserEntity entity);

}
