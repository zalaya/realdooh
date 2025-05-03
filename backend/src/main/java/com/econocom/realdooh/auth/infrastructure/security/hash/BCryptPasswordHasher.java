package com.econocom.realdooh.auth.infrastructure.security.hash;

import com.econocom.realdooh.auth.domain.port.outbound.security.hash.PasswordHasher;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.Password;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder encoder;

    @Override
    public HashedPassword hash(Password rawPassword) {
        return new HashedPassword(encoder.encode(rawPassword.getValue()));
    }

    @Override
    public boolean matches(Password rawPassword, HashedPassword hashedPassword) {
        return encoder.matches(rawPassword.getValue(), hashedPassword.getValue());
    }

}
