package com.econocom.realdooh.auth.infrastructure.rest.error;

import com.econocom.realdooh.shared.infrastructure.rest.error.ApiError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {

    @Test
    void givenMessageAndStatus_whenConstructed_thenFieldsAreSet() {
        // Given
        String message = "Message";
        int status = 400;

        // When
        ApiError error = new ApiError(message, status);

        // Then
        assertAll(
            () -> assertEquals(message, error.getMessage()),
            () -> assertEquals(status, error.getStatus()),
            () -> assertNotNull(error.getTimestamp())
        );
    }

}
