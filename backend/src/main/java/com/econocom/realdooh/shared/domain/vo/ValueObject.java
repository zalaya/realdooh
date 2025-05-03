package com.econocom.realdooh.shared.domain.vo;

import com.econocom.realdooh.shared.domain.exception.InvalidValueObjectException;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class ValueObject<T> {

    protected final T value;

    protected ValueObject(T value) {
        if (value == null) {
            throw new InvalidValueObjectException(this.getClass().getSimpleName() + " cannot be null");
        }

        this.value = validate(value);
    }

    protected T validate(T value) {
        return value;
    }

}
