package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;

import lombok.Value;

@Value
public class Tokens {

    AccessToken accessToken;
    RefreshToken refreshToken;

}
