package com.econocom.realdooh.auth.infrastructure.rest.error;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ApiError {

    String message;
    int status;
    LocalDateTime timestamp;

    public ApiError(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

}
