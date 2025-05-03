package com.econocom.realdooh.auth.domain.vo.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public final class AccessToken extends Token {

    public AccessToken(String value) {
        super(value);
    }

    public long getExpiresIn() {
        DecodedJWT decoded = JWT.decode(value);

        long expirationTime = decoded.getExpiresAt().getTime() / 1000;
        long now = System.currentTimeMillis() / 1000;

        return expirationTime - now;
    }

}
