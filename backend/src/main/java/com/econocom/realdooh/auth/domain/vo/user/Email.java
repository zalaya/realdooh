package com.econocom.realdooh.auth.domain.vo.user;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;
import com.econocom.realdooh.shared.domain.vo.ValueObject;

public final class Email extends ValueObject<String> {

    public Email(String value) {
        super(value);
    }

    @Override
    protected String validate(String value) {
        if (value.isBlank()) {
            throw new InvalidValueObjectException("Invalid Email");
        }

        return value;
    }

}
