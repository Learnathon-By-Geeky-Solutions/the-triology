package com.smart.health.care.management.system.mappertest;

import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import com.smart.health.care.management.system.dto.HealthProfileDto;
import com.smart.health.care.management.system.mapper.HealthProfileMapper;
import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthProfileMapperTest {

    private final HealthProfileMapper healthProfileMapper = new HealthProfileMapper();

    @Test
    void testToDto() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1);

        HealthProfile healthProfile = new HealthProfile();
        healthProfile.setId(10L);
        healthProfile.setPatient(patient);
        healthProfile.setHeight(170.5);
        healthProfile.setWeight(65.0);
        healthProfile.setBloodGroup("A+");
        healthProfile.setAllergies(Arrays.asList("Peanuts", "Dust"));
        healthProfile.setMedicalHistory("Asthma");
        healthProfile.setConditions("Hypertension");

        // Act
        HealthProfileDto dto = healthProfileMapper.toDto(healthProfile);

        // Assert
        assertEquals(10L, dto.getId());
        assertEquals(1L, dto.getPatientId());
        assertEquals(170.5, dto.getHeight());
        assertEquals(65.0, dto.getWeight());
        assertEquals("A+", dto.getBloodGroup());
        assertEquals(Arrays.asList("Peanuts", "Dust"), dto.getAllergies());
        assertEquals("Asthma", dto.getMedicalHistory());
        assertEquals("Hypertension", dto.getConditions());
    }

    @Test
    void testToEntity() {
        // Arrange
        HealthProfileCreateDto createDto = new HealthProfileCreateDto();
        createDto.setHeight(180.2);
        createDto.setWeight(75.5);
        createDto.setBloodGroup("B+");
        createDto.setAllergies(Arrays.asList("Pollen", "Cats"));
        createDto.setMedicalHistory("Diabetes");
        createDto.setConditions("Arthritis");

        Patient patient = new Patient();
        patient.setId(2);

        // Act
        HealthProfile entity = healthProfileMapper.toEntity(createDto, patient);

        // Assert
        assertEquals(patient, entity.getPatient());
        assertEquals(180.2, entity.getHeight());
        assertEquals(75.5, entity.getWeight());
        assertEquals("B+", entity.getBloodGroup());
        assertEquals(Arrays.asList("Pollen", "Cats"), entity.getAllergies());
        assertEquals("Diabetes", entity.getMedicalHistory());
        assertEquals("Arthritis", entity.getConditions());
    }
}
