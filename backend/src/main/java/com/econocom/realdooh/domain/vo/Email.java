package com.econocom.realdooh.domain.vo;

import lombok.Value;

@Value
public class Email {

    String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        this.value = value;
    }

}
