package com.econocom.realdooh.auth.domain.port.outbound.security.token;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.token.AccessToken;
import com.econocom.realdooh.auth.domain.vo.token.RefreshToken;

public interface TokenGenerator {

    AccessToken generateAccessToken(User user);
    RefreshToken generateRefreshToken(User user);

}
