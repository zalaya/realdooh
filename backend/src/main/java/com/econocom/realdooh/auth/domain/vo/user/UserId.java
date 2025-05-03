package com.econocom.realdooh.auth.domain.vo.user;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;
import com.econocom.realdooh.shared.domain.vo.ValueObject;

public final class UserId extends ValueObject<Long> {

    public UserId(Long value) {
        super(value);
    }

    @Override
    protected Long validate(Long value) {
        if (value <= 0) {
            throw new InvalidValueObjectException("Invalid UserId");
        }

        return value;
    }

}
