package com.econocom.realdooh.auth.infrastructure.rest.controller;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.port.inbound.LoginUseCase;
import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoginUseCase loginUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ENDPOINT = "/auth/login/login";

    @ParameterizedTest
    @ValueSource(strings = {"invalid-email", "user@", "", "   "})
    void givenInvalidEmail_whenLogin_thenReturnBadRequest(String email) throws Exception {
        // Given
        Map<String, String> request = Map.of(
            "email", email,
            "password", "Valid Password"
        );

        // When / Then
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void givenInvalidPassword_whenLogin_thenReturnBadRequest(String password) throws Exception {
        // Given
        Map<String, String> request = Map.of(
            "email", "user@example.com",
            "password", password
        );

        // When / Then
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidCredentials_whenLogin_thenReturnBadRequest() throws Exception {
        // Given
        Map<String, String> request = Map.of(
            "email", "user@example.com",
            "password", "Valid Password"
        );

        when(loginUseCase.login(any(Credentials.class))).thenThrow(new InvalidValueObjectException("Invalid Credentials"));

        // When / Then
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Invalid Credentials"))
            .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void givenUnauthorizedPassword_whenLogin_thenReturnUnauthorized() throws Exception {
        // Given
        Map<String, String> request = Map.of(
            "email", "user@example.com",
            "password", "Wrong Password"
        );

        when(loginUseCase.login(any(Credentials.class))).thenThrow(new IllegalArgumentException("Invalid credentials"));

        // When / Then
        mockMvc.perform(post("/auth/login/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.message").value("Invalid credentials"))
            .andExpect(jsonPath("$.status").value(401));
    }
}
