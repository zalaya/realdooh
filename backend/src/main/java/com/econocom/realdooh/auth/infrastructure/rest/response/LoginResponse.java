package com.econocom.realdooh.auth.infrastructure.rest.response;

import lombok.Value;

@Value
public class LoginResponse {

    String accessToken;
    String refreshToken;

}
