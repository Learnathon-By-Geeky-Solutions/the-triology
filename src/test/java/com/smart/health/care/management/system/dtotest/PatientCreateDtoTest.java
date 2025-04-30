package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientCreateDtoTest {

    @Test
    void testPatientCreateDto() {

        PatientCreateDto patientCreateDto = new PatientCreateDto();
        Long expectedPatientId = 123L;
        String expectedName = "John Doe";
        String expectedGender = "Male";
        String expectedEmail = "john.doe@example.com";
        String expectedPhone = "+1234567890";
        String expectedAddress = "123 Main Street, City, Country";

        patientCreateDto.setPatientId(expectedPatientId);
        patientCreateDto.setName(expectedName);
        patientCreateDto.setGender(expectedGender);
        patientCreateDto.setEmail(expectedEmail);
        patientCreateDto.setPhone(expectedPhone);
        patientCreateDto.setAddress(expectedAddress);

        assertEquals(expectedPatientId, patientCreateDto.getPatientId());
        assertEquals(expectedName, patientCreateDto.getName());
        assertEquals(expectedGender, patientCreateDto.getGender());
        assertEquals(expectedEmail, patientCreateDto.getEmail());
        assertEquals(expectedPhone, patientCreateDto.getPhoneNumber());
        assertEquals(expectedAddress, patientCreateDto.getAddress());
    }

    @Test
    void testName() {

        PatientCreateDto patientCreateDto = new PatientCreateDto();
        String expectedName = "John Doe";

        patientCreateDto.setName(expectedName);

        assertEquals(expectedName, patientCreateDto.getName());
    }

    @Test
    void testPhoneNumber() {

        PatientCreateDto patientCreateDto = new PatientCreateDto();
        String expectedPhone = "+1234567890";

        patientCreateDto.setPhone(expectedPhone);

        assertEquals(expectedPhone, patientCreateDto.getPhoneNumber());
    }
}