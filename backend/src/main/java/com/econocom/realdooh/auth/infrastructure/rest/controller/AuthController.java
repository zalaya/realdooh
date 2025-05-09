package com.econocom.realdooh.auth.infrastructure.rest.controller;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.model.Tokens;
import com.econocom.realdooh.auth.domain.port.inbound.LoginUseCase;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.Password;
import com.econocom.realdooh.auth.infrastructure.rest.mapper.LoginRequestMapper;
import com.econocom.realdooh.auth.infrastructure.rest.mapper.LoginResponseMapper;
import com.econocom.realdooh.auth.infrastructure.rest.request.LoginRequest;
import com.econocom.realdooh.auth.infrastructure.rest.response.LoginResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final LoginRequestMapper requestMapper;
    private final LoginResponseMapper responseMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Credentials credentials = requestMapper.toDomain(request);
        Tokens tokens = loginUseCase.login(credentials);
        LoginResponse response = responseMapper.toDto(tokens);

        return ResponseEntity.ok(response);
    }

}
