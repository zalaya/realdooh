package com.econocom.realdooh.auth.infrastructure.rest.mapper;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.infrastructure.rest.request.LoginRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginRequestMapper {

    @Mapping(target = "email", expression = "java(new Email(request.getEmail()))")
    @Mapping(target = "password", expression = "java(new Password(request.getPassword()))")
    Credentials toDomain(LoginRequest request);

}
