package com.econocom.realdooh.auth.infrastructure.rest.mapper;

import com.econocom.realdooh.auth.domain.model.Tokens;
import com.econocom.realdooh.auth.infrastructure.rest.response.LoginResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginResponseMapper {

    @Mapping(target = "tokenType", ignore = true)
    @Mapping(source = "accessToken.value",  target = "accessToken")
    @Mapping(source = "refreshToken.value", target = "refreshToken")
    LoginResponse toDto(Tokens tokens);

}
