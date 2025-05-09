package com.econocom.realdooh.auth.application.usecase;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.model.Tokens;
import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.port.outbound.persistence.UserRepository;
import com.econocom.realdooh.auth.domain.port.outbound.security.hash.PasswordHasher;
import com.econocom.realdooh.auth.domain.port.outbound.security.token.TokenGenerator;
import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;
import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.Password;
import com.econocom.realdooh.auth.domain.vo.user.UserId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    private UserRepository repository;
    private PasswordHasher passwordHasher;
    private TokenGenerator tokenGenerator;
    private LoginService loginService;

    private final UserId id = new UserId(1L);
    private final Email email = new Email("Email");
    private final Password password = new Password("Password");
    private final HashedPassword hashedPassword = new HashedPassword("HashedPassword");
    private final User user = new User(id, email, hashedPassword);

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        passwordHasher = mock(PasswordHasher.class);
        tokenGenerator = mock(TokenGenerator.class);
        loginService = new LoginService(repository, passwordHasher, tokenGenerator);
    }

    @Test
    void givenValidCredentials_whenLogin_thenReturnTokens() {
        // Given
        when(repository.findByEmail(email)).thenReturn(user);
        when(passwordHasher.matches(password, hashedPassword)).thenReturn(true);
        when(tokenGenerator.generateAccessToken(user)).thenReturn(new AccessToken("access-token"));
        when(tokenGenerator.generateRefreshToken(user)).thenReturn(new RefreshToken("refresh-token"));
        Credentials credentials = new Credentials(email, password);

        // When
        Tokens tokens = loginService.login(credentials);

        // Then
        verify(repository).findByEmail(email);
        verify(passwordHasher).matches(password, hashedPassword);
        verify(tokenGenerator).generateAccessToken(user);
        verify(tokenGenerator).generateRefreshToken(user);
    }

    @Test
    void givenWrongPassword_whenLogin_thenThrowException() {
        // Given
        when(repository.findByEmail(email)).thenReturn(user);
        when(passwordHasher.matches(password, hashedPassword)).thenReturn(false);
        Credentials credentials = new Credentials(email, password);

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> loginService.login(credentials));
    }

}
