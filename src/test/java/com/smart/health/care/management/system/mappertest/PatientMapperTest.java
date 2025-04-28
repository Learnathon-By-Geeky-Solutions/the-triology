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
    String date = "05/05/1995";

    @Test
    void testToDto() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
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
        dto.setPhone("01624728800");
        dto.setDateOfBirth(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Act
        Patient patient = patientMapper.toEntity(dto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);

        // Assert
        assertEquals("Jane Doe", patient.getName());
        assertEquals("01624728800", patient.getPhoneNumber());
        assertEquals(localDate, patient.getDateOfBirth());
    }
}
