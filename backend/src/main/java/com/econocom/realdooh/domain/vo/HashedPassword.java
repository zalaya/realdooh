package com.econocom.realdooh.domain.vo;

import lombok.Value;

@Value
public class HashedPassword {

    String value;

    public HashedPassword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("HashedPassword cannot be null or blank");
        }

        this.value = value;
    }

}
