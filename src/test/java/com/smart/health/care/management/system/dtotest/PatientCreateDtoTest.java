package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientCreateDtoTest {

    @Test
    void testPatientCreateDto() {
        // Arrange
        PatientCreateDto patientCreateDto = new PatientCreateDto();
        Long expectedPatientId = 123L;
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String expectedGender = "Male";
        String expectedEmail = "john.doe@example.com";
        String expectedPhone = "+1234567890";
        String expectedAddress = "123 Main Street, City, Country";

        // Act
        patientCreateDto.setPatientId(expectedPatientId);
        patientCreateDto.setFirstName(expectedFirstName);
        patientCreateDto.setLastName(expectedLastName);
        patientCreateDto.setGender(expectedGender);
        patientCreateDto.setEmail(expectedEmail);
        patientCreateDto.setPhone(expectedPhone);
        patientCreateDto.setAddress(expectedAddress);

        // Assert
        assertEquals(expectedPatientId, patientCreateDto.getPatientId());
        assertEquals(expectedFirstName, patientCreateDto.getFirstName());
        assertEquals(expectedLastName, patientCreateDto.getLastName());
        assertEquals(expectedGender, patientCreateDto.getGender());
        assertEquals(expectedEmail, patientCreateDto.getEmail());
        assertEquals(expectedPhone, patientCreateDto.getPhone());
        assertEquals(expectedAddress, patientCreateDto.getAddress());
    }
}
