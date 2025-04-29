package com.econocom.realdooh.domain.vo;

import lombok.Value;

@Value
public class Password {

    String value;

    public Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }

        this.value = value;
    }

}
