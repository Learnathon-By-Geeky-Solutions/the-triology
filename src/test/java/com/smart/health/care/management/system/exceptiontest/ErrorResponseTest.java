package com.smart.health.care.management.system.exceptiontest;

import com.smart.health.care.management.system.exception.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    @Test
    void testErrorResponseConstructorAndGetters() {
        // Arrange
        String expectedMessage = "Resource not found";
        int expectedStatus = 404;
        LocalDateTime expectedTimestamp = LocalDateTime.now();

        // Act
        ErrorResponse errorResponse = new ErrorResponse(expectedMessage, expectedStatus, expectedTimestamp);

        // Assert
        assertEquals(expectedMessage, errorResponse.getMessage());
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedTimestamp, errorResponse.getTimestamp());
    }
}
