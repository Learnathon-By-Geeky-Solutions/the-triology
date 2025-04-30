package com.smart.health.care.management.system.exceptiontest;

import com.smart.health.care.management.system.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleResourceNotFound() {

        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

        ResponseEntity<ErrorResponse> responseEntity = handler.handleResourceNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
    }

    @Test
    void testHandleInvalidInput() {
        InvalidInputException exception = new InvalidInputException("Invalid input");

        ResponseEntity<ErrorResponse> responseEntity = handler.handleInvalidInput(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid input", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
    }

    @Test
    void testHandleBusinessLogic() {

        BusinessLogicException exception = new BusinessLogicException("Business logic error");

        ResponseEntity<ErrorResponse> responseEntity = handler.handleBusinessLogic(exception);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals("Business logic error", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
    }

    @Test
    void testHandleGenericException() {

        Exception exception = new Exception("Generic exception");

        ResponseEntity<ErrorResponse> responseEntity = handler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error. Please contact support.", responseEntity.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        assertNotNull(responseEntity.getBody().getTimestamp());
    }
}
