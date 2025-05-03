package com.econocom.realdooh.auth.application.usecase;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.inbound.LoginUseCase;
import com.econocom.realdooh.auth.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.auth.domain.port.outbound.security.hash.PasswordHasher;
import com.econocom.realdooh.auth.domain.port.outbound.security.token.TokenGenerator;
import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.model.Tokens;
import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final UserRepository repository;
    private final PasswordHasher passwordHasher;
    private final TokenGenerator tokenGenerator;

    @Override
    public Tokens login(Credentials credentials) {
        User user = repository.findByEmail(credentials.getEmail()).orElseThrow();

        if (!passwordHasher.matches(credentials.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        AccessToken accessToken = tokenGenerator.generateAccessToken(user);
        RefreshToken refreshToken = tokenGenerator.generateRefreshToken(user);

        return new Tokens(accessToken, refreshToken);
    }

}
