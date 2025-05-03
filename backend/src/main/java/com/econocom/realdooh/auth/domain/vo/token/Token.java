package com.econocom.realdooh.auth.domain.vo.token;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;
import com.econocom.realdooh.shared.domain.vo.ValueObject;

public abstract class Token extends ValueObject<String> {

    public Token(String value) {
        super(value);
    }

    @Override
    protected String validate(String value) {
        if (value.isBlank()) {
            throw new InvalidValueObjectException("Invalid Token Format");
        }

        return value;
    }

}
