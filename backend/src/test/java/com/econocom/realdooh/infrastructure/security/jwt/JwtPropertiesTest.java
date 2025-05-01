package com.econocom.realdooh.infrastructure.security.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.*;

class JwtPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(Configuration.class);

    @EnableConfigurationProperties(JwtProperties.class)
    static class Configuration {}

    @Test
    void givenProperties_whenContextLoaded_thenPropertiesAreBound() {
        // Given
        String[] properties = {
            "jwt.secret=this-is-a-secret",
            "jwt.expiration=1234"
        };

        // When / Then
        contextRunner.withPropertyValues(properties).run(context -> {
            JwtProperties jwtProperties = context.getBean(JwtProperties.class);

            assertAll(
                () -> assertEquals("this-is-a-secret", jwtProperties.getSecret()),
                () -> assertEquals(1234, jwtProperties.getExpiration())
            );
        });
    }

    @Test
    void givenInvalidSecret_whenContextLoaded_thenThrowException() {
        // Given
        String[] properties = {
            "jwt.secret=",
            "jwt.expiration=1234"
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(JwtProperties.class);
            });
        });
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -100})
    void givenInvalidExpiration_whenContextLoaded_thenThrowException(long expiration) {
        // Given
        String[] properties = {
            "jwt.secret=this-is-a-secret",
            "jwt.expiration=" + expiration
        };

        // When / Then
        assertThrows(IllegalStateException.class, () -> {
            contextRunner.withPropertyValues(properties).run(context -> {
                context.getBean(JwtProperties.class);
            });
        });
    }

}
