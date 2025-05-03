package com.econocom.realdooh.shared.domain.exception;

import java.io.Serial;

public class InvalidValueObjectException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -963369108519507202L;

    public InvalidValueObjectException(String message) {
        super(message);
    }

}
