package com.econocom.realdooh.auth.domain.port.outbound.security.hash;

import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.Password;

public interface PasswordHasher {

    HashedPassword hash(Password rawPassword);
    boolean matches(Password rawPassword, HashedPassword hashedPassword);

}
