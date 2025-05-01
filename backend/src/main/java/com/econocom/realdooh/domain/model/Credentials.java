package com.econocom.realdooh.domain.model;

import com.econocom.realdooh.domain.vo.Password;
import com.econocom.realdooh.domain.vo.Username;

import lombok.Value;

@Value
public class Credentials {

    Username username;
    Password password;

}
