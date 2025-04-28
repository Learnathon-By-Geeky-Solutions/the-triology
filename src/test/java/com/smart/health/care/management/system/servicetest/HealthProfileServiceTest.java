package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.HealthProfileMapper;
import com.smart.health.care.management.system.repository.HealthProfileRepo;
import com.smart.health.care.management.system.service.HealthProfileService;
import com.smart.health.care.management.system.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthProfileServiceTest {

    private HealthProfileRepo healthProfileRepo;
    private HealthProfileMapper healthProfileMapper;
    private PatientService patientService;
    private HealthProfileService healthProfileService;

    @BeforeEach
    void setUp() {
        healthProfileRepo = mock(HealthProfileRepo.class);
        healthProfileMapper = mock(HealthProfileMapper.class);
        patientService = mock(PatientService.class);
        healthProfileService = new HealthProfileService(healthProfileRepo, healthProfileMapper, patientService);
    }

    @Test
    void testGetHealthProfileByPatientId_NotFound() {
        // Mock the repository method to return null for patient ID 1
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        // Ensure that the ResourceNotFoundException is thrown when no profile is found
        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.getHealthProfileByPatientId(1L));
    }

    @Test
    void testUpdateHealthProfile_NotFound() {
        // Mock the repository method to return null for patient ID 1
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        // Create the update DTO
        HealthProfileCreateDto updateDto = new HealthProfileCreateDto();
        updateDto.setPatientId(1L);
        updateDto.setBloodGroup("A+");
        updateDto.setHeight(175);
        updateDto.setWeight(75);
        updateDto.setConditions("Good");

        // Ensure that the ResourceNotFoundException is thrown
        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.updateHealthProfile(1L, updateDto));
    }

    @Test
    void testDeleteHealthProfile_NotFound() {
        // Mock the repository method to return null for patient ID 1
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        // Ensure that the ResourceNotFoundException is thrown
        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.deleteHealthProfile(1L));
    }
}
