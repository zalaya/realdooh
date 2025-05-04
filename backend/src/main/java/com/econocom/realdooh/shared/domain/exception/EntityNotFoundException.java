package com.econocom.realdooh.shared.domain.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6030502801729355863L;

    public EntityNotFoundException(String message) {
        super(message);
    }

}
