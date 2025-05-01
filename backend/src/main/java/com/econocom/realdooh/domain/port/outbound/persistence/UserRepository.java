package com.econocom.realdooh.domain.port.outbound.persistence;

import com.econocom.realdooh.domain.model.User;
import com.econocom.realdooh.domain.vo.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(Email email);

}
