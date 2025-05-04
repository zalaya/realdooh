package com.econocom.realdooh.auth.infrastructure.security.configuration.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.*;

class TokenPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(Configuration.class);

    @EnableConfigurationProperties(TokenProperties.class)
    static class Configuration {}

    @Test
    void givenValidProperties_whenContextLoaded_thenPropertiesAreBoundCorrectly() {
        // Given
        String[] properties = {
            "auth.token.secret=this-is-a-secret",
            "auth.token.issuer=realdooh-auth",
            "auth.token.access-token-expiration=3600",
            "auth.token.refresh-token-expiration=86400"
        };

        // When / Then
        contextRunner.withPropertyValues(properties).run(context -> {
            TokenProperties tokenProperties = context.getBean(TokenProperties.class);

            assertAll(
                () -> assertEquals("this-is-a-secret", tokenProperties.getSecret()),
                () -> assertEquals("realdooh-auth", tokenProperties.getIssuer()),
                () -> assertEquals(3600L, tokenProperties.getAccessTokenExpiration()),
                () -> assertEquals(86400L, tokenProperties.getRefreshTokenExpiration())
            );
        });
    }

    @Test
    void givenEmptySecret_whenContextLoaded_thenValidationFails() {
        // Given
        String[] properties = {
            "auth.token.secret=",
            "auth.token.issuer=realdooh-auth",
            "auth.token.access-token-expiration=3600",
            "auth.token.refresh-token-expiration=86400"
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(TokenProperties.class);
            });
        });
    }

    @Test
    void givenEmptyIssuer_whenContextLoaded_thenValidationFails() {
        // Given
        String[] properties = {
            "auth.token.secret=this-is-a-secret",
            "auth.token.issuer=",
            "auth.token.access-token-expiration=3600",
            "auth.token.refresh-token-expiration=86400"
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(TokenProperties.class);
            });
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1})
    void givenInvalidAccessTokenExpiration_whenContextLoaded_thenValidationFails(long value) {
        // Given
        String[] properties = {
            "auth.token.secret=this-is-a-secret",
            "auth.token.issuer=realdooh-auth",
            "auth.token.access-token-expiration=" + value,
            "auth.token.refresh-token-expiration=86400"
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(TokenProperties.class);
            });
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -10})
    void givenInvalidRefreshTokenExpiration_whenContextLoaded_thenValidationFails(long value) {
        // Given
        String[] properties = {
            "auth.token.secret=this-is-a-secret",
            "auth.token.issuer=realdooh-auth",
            "auth.token.access-token-expiration=3600",
            "auth.token.refresh-token-expiration=" + value
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(TokenProperties.class);
            });
        });
    }

}
