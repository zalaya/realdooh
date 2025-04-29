package com.econocom.realdooh.domain.vo;

import lombok.Value;

@Value
public class Username {

    String value;

    public Username(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }

        this.value = value;
    }

}
