package com.econocom.realdooh.domain.port.outbound;

import java.util.Map;

public interface TokenProvider {

    String generate(Map<String, Object> claims);
    boolean validate(String token);

}
