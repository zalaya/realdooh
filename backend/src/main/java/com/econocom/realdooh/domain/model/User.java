package com.econocom.realdooh.domain.model;

import com.econocom.realdooh.domain.vo.Email;
import com.econocom.realdooh.domain.vo.HashedPassword;

import lombok.Value;

@Value
public class User {

    Email email;
    HashedPassword hashedPassword;

}
