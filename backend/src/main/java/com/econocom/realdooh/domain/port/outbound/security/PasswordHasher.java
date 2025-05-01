package com.econocom.realdooh.domain.port.outbound.security;

public interface PasswordHasher {

    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);

}
