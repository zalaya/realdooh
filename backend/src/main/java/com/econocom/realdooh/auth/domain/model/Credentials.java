package com.econocom.realdooh.auth.domain.model;

import com.econocom.realdooh.auth.domain.vo.user.Email;
import com.econocom.realdooh.auth.domain.vo.user.Password;

import lombok.Value;

@Value
public class Credentials {

    Email email;
    Password password;

}
