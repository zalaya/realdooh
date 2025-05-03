package com.econocom.realdooh.auth.infrastructure.security.configuration.properties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "auth.token")
public class TokenProperties {

    @NotEmpty
    private String secret;

    @NotEmpty
    private String issuer;

    @Min(1)
    private long accessTokenExpiration;

    @Min(1)
    private long refreshTokenExpiration;

}
