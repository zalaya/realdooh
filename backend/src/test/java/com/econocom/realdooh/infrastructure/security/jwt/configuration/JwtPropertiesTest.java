package com.econocom.realdooh.infrastructure.security.jwt.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(Configuration.class);

    @EnableConfigurationProperties(JwtProperties.class)
    static class Configuration {}

    @Test
    void givenProperties_whenContextLoaded_thenPropertiesAreBound() {
        contextRunner
            .withPropertyValues(
                "jwt.secret=this-is-not-a-secret",
                "jwt.expiration=1234"
            )
            .run(context -> {
                JwtProperties jwtProperties = context.getBean(JwtProperties.class);

                assertEquals("this-is-not-a-secret", jwtProperties.getSecret());
                assertEquals(1234, jwtProperties.getExpiration());
            });
    }

    @Test
    void givenEmptySecret_whenContextLoaded_thenThrowException() {
        contextRunner
            .withPropertyValues(
                "jwt.secret=",
                "jwt.expiration=1234"
            )
            .run(context -> {
                assertThrows(IllegalStateException.class, () -> context.getBean(JwtProperties.class));
            });
    }

    @Test
    void givenZeroExpiration_whenContextLoaded_thenThrowException() {
        contextRunner
            .withPropertyValues(
                "jwt.secret=valid-secret",
                "jwt.expiration=0"
            )
            .run(context -> {
                assertThrows(IllegalStateException.class, () -> context.getBean(JwtProperties.class));
            });
    }

    @Test
    void givenNegativeExpiration_whenContextLoaded_thenThrowException() {
        contextRunner
            .withPropertyValues(
                "jwt.secret=valid-secret",
                "jwt.expiration=-100"
            )
            .run(context -> {
                assertThrows(IllegalStateException.class, () -> context.getBean(JwtProperties.class));
            });
    }

}
