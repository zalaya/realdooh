package com.econocom.realdooh.domain.port.inbound;

import com.econocom.realdooh.domain.Credentials;

public interface LoginUseCase {

    String login(Credentials credentials);

}
