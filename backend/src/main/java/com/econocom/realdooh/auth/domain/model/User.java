package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.HashedPassword;
import com.econocom.realdooh.auth.domain.vo.user.UserId;

import lombok.Value;

@Value
public class User {

    UserId id;
    Email email;
    HashedPassword password;

}
