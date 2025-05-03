package com.econocom.realdooh.auth.domain.port.inbound;

import com.econocom.realdooh.auth.domain.model.Credentials;
import com.econocom.realdooh.auth.domain.model.Tokens;

public interface LoginUseCase {

    Tokens login(Credentials credentials);

}
