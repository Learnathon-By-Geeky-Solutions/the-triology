package com.smart.health.care.management.system.mappertest;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.mapper.PatientMapper;
import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PatientMapperTest {

    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        patientMapper = new PatientMapper();
    }

    @Test
    void testToDto() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("John Doe");
        patient.setPhoneNumber("+1234567890");

        // Act
        PatientDto dto = patientMapper.toDto(patient);

        // Assert
        assertEquals(1, dto.getId());
        assertEquals("John Doe", dto.getName());
        assertEquals("+1234567890", dto.getPhoneNumber());
    }

    @Test
    void testToEntity() {
        // Arrange
        PatientCreateDto dto = new PatientCreateDto();
        dto.setName("Jane Doe");
        dto.setPhoneNumber("+0987654321");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("05/05/1995", formatter);
        dto.setDateOfBirth(date);

        // Act
        Patient patient = patientMapper.toEntity(dto);

        // Assert
        assertEquals("Jane Doe", patient.getName());
        assertEquals("+0987654321", patient.getPhoneNumber());
        assertEquals(date, patient.getDateOfBirth());
    }
}
