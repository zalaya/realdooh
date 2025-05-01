package com.econocom.realdooh.domain;

import com.econocom.realdooh.domain.vo.Password;
import com.econocom.realdooh.domain.vo.Email;

import lombok.Value;

@Value
public class Credentials {

    Email email;
    Password password;

}
