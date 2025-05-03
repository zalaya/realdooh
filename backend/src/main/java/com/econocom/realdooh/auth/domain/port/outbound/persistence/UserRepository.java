package com.econocom.realdooh.auth.domain.port.outbound.persistence;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(Email email);

}
