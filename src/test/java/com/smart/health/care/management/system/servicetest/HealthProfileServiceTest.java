package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import com.smart.health.care.management.system.dto.HealthProfileDto;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.HealthProfileMapper;
import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.HealthProfileRepo;
import com.smart.health.care.management.system.service.HealthProfileService;
import com.smart.health.care.management.system.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void testGetAllHealthProfiles_Success() {
        HealthProfile profile = new HealthProfile();
        when(healthProfileRepo.findAll()).thenReturn(List.of(profile));
        when(healthProfileMapper.toDto(profile)).thenReturn(new HealthProfileDto());

        List<HealthProfileDto> result = healthProfileService.getAllHealthProfiles();

        assertEquals(1, result.size());
        verify(healthProfileRepo, times(1)).findAll();
    }

    @Test
    void testGetHealthProfileByPatientId_Success() {
        HealthProfile profile = new HealthProfile();
        when(healthProfileRepo.findByPatientId(1)).thenReturn(profile);
        when(healthProfileMapper.toDto(profile)).thenReturn(new HealthProfileDto());

        HealthProfileDto result = healthProfileService.getHealthProfileByPatientId(1L);

        assertNotNull(result);
    }

    @Test
    void testGetHealthProfileByPatientId_NotFound() {
        when(healthProfileRepo.findByPatientId(1)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.getHealthProfileByPatientId(1L));
    }

    @Test
    void testCreateHealthProfile_Success() {
        HealthProfileCreateDto createDto = new HealthProfileCreateDto();
        createDto.setPatientId(1L);
        createDto.setBloodGroup("O+");
        createDto.setHeight(170);
        createDto.setWeight(70);
        createDto.setConditions("Healthy");

        Patient patient = new Patient();
        HealthProfile healthProfile = new HealthProfile();
        HealthProfile savedProfile = new HealthProfile();

        when(patientService.getPatientEntityById(1)).thenReturn(patient);
        when(healthProfileMapper.toEntity(createDto, patient)).thenReturn(healthProfile);
        when(healthProfileRepo.save(healthProfile)).thenReturn(savedProfile);
        when(healthProfileMapper.toDto(savedProfile)).thenReturn(new HealthProfileDto());

        HealthProfileDto result = healthProfileService.createHealthProfile(createDto);

        assertNotNull(result);
    }

    @Test
    void testCreateHealthProfile_InvalidInput() {
        HealthProfileCreateDto createDto = new HealthProfileCreateDto();
        createDto.setPatientId(null); // Missing ID

        assertThrows(InvalidInputException.class, () -> healthProfileService.createHealthProfile(createDto));
    }

    @Test
    void testUpdateHealthProfile_Success() {
        HealthProfileCreateDto updateDto = new HealthProfileCreateDto();
        updateDto.setPatientId(1L);
        updateDto.setBloodGroup("A+");
        updateDto.setHeight(175);
        updateDto.setWeight(75);
        updateDto.setConditions("Good");

        HealthProfile existingProfile = new HealthProfile();
        when(healthProfileRepo.findByPatientId(1)).thenReturn(existingProfile);

        String result = healthProfileService.updateHealthProfile(1L, updateDto);

        assertEquals("Health Profile updated successfully", result);
        verify(healthProfileRepo, times(1)).save(existingProfile);
    }

    @Test
    void testUpdateHealthProfile_NotFound() {
        when(healthProfileRepo.findByPatientId(1)).thenReturn(null);

        HealthProfileCreateDto updateDto = new HealthProfileCreateDto();
        updateDto.setPatientId(1L);
        updateDto.setBloodGroup("A+");
        updateDto.setHeight(175);
        updateDto.setWeight(75);
        updateDto.setConditions("Good");

        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.updateHealthProfile(1L, updateDto));
    }

    @Test
    void testDeleteHealthProfile_Success() {
        HealthProfile hp = new HealthProfile();
        when(healthProfileRepo.findByPatientId(1)).thenReturn(hp);

        String result = healthProfileService.deleteHealthProfile(1L);

        assertEquals("Successfully deleted Health Profile with ID 1", result);
        verify(healthProfileRepo, times(1)).delete(hp);
    }

    @Test
    void testDeleteHealthProfile_NotFound() {
        when(healthProfileRepo.findByPatientId(1)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> healthProfileService.deleteHealthProfile(1L));
    }
}