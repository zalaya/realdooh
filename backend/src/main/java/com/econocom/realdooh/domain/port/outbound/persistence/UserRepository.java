package com.econocom.realdooh.domain.port.outbound.persistence;

import com.econocom.realdooh.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail();

}
