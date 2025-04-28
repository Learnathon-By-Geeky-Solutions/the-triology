package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.PatientDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatientDtoTest {

    @Test
    void testConstructorInitialization() {
        PatientDto patientDto = new PatientDto(1L, "John Doe", "01712345678");

        assertAll(
                () -> assertEquals(1, patientDto.getId(), "ID should be initialized correctly"),
                () -> assertEquals("John Doe", patientDto.getName(), "Name should be initialized correctly"),
                () -> assertEquals("01712345678", patientDto.getPhoneNumber(), "Phone number should be initialized correctly")
        );
    }

    @Test
    void testSettersAndGetters() {
        PatientDto patientDto = new PatientDto(0L, "", "");

        patientDto.setId(2L);
        patientDto.setName("Jane Smith");
        patientDto.setPhoneNumber("01898765432");

        assertAll(
                () -> assertEquals(2, patientDto.getId(), "ID should be set correctly"),
                () -> assertEquals("Jane Smith", patientDto.getName(), "Name should be set correctly"),
                () -> assertEquals("01898765432", patientDto.getPhoneNumber(), "Phone number should be set correctly")
        );
    }
}