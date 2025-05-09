package com.econocom.realdooh.auth.domain.port.outbound.persistence;

import com.econocom.realdooh.auth.domain.model.User;
import com.econocom.realdooh.auth.domain.vo.user.Email;

public interface UserRepository {

    User findByEmail(Email email);

}
