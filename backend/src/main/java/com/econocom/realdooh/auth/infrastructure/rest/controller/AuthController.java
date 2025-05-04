package com.econocom.realdooh.auth.infrastructure.rest.controller;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.model.Tokens;
import com.econocom.realdooh.auth.domain.port.inbound.LoginUseCase;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.Password;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Credentials credentials = new Credentials(new Email(request.getEmail()), new Password(request.getPassword()));
        Tokens tokens = loginUseCase.login(credentials);

        return ResponseEntity.ok(new LoginResponse(tokens.getAccessToken().getValue(), tokens.getRefreshToken().getValue()));
    }

}
