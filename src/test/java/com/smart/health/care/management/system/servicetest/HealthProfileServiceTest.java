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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthProfileServiceTest {

    @Mock
    private HealthProfileRepo healthProfileRepo;

    @Mock
    private HealthProfileMapper healthProfileMapper;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private HealthProfileService healthProfileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHealthProfiles() {
        HealthProfile profile = new HealthProfile();
        when(healthProfileRepo.findAll()).thenReturn(Collections.singletonList(profile));
        when(healthProfileMapper.toDto(profile)).thenReturn(new HealthProfileDto());

        List<HealthProfileDto> result = healthProfileService.getAllHealthProfiles();

        assertEquals(1, result.size());
        verify(healthProfileRepo, times(1)).findAll();
    }

    @Test
    void testGetHealthProfileByPatientId_Success() {
        HealthProfile profile = new HealthProfile();
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(profile);
        when(healthProfileMapper.toDto(profile)).thenReturn(new HealthProfileDto());

        HealthProfileDto result = healthProfileService.getHealthProfileByPatientId(1L);

        assertNotNull(result);
        verify(healthProfileRepo, times(1)).findByPatient_Id(1L);
    }

    @Test
    void testGetHealthProfileByPatientId_NotFound() {
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            healthProfileService.getHealthProfileByPatientId(1L);
        });
    }

    @Test
    void testCreateHealthProfile_Success() {
        HealthProfileCreateDto createDto = new HealthProfileCreateDto();
        createDto.setPatientId(1L);
        createDto.setBloodGroup("A+");
        createDto.setConditions("Healthy");
        createDto.setHeight(170.0);
        createDto.setWeight(70.0);

        Patient patient = new Patient();
        HealthProfile profile = new HealthProfile();

        when(patientService.getPatientEntityById(1L)).thenReturn(patient);
        when(healthProfileMapper.toEntity(createDto, patient)).thenReturn(profile);
        when(healthProfileRepo.save(profile)).thenReturn(profile);
        when(healthProfileMapper.toDto(profile)).thenReturn(new HealthProfileDto());

        HealthProfileDto result = healthProfileService.createHealthProfile(createDto);

        assertNotNull(result);
        verify(healthProfileRepo, times(1)).save(profile);
    }

    @Test
    void testUpdateHealthProfile_Success() {
        HealthProfile existingProfile = new HealthProfile();
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(existingProfile);

        HealthProfileCreateDto updateDto = new HealthProfileCreateDto();
        updateDto.setPatientId(1L);
        updateDto.setBloodGroup("O+");
        updateDto.setConditions("Recovered");
        updateDto.setHeight(180.0);
        updateDto.setWeight(75.0);

        String result = healthProfileService.updateHealthProfile(1L, updateDto);

        assertEquals("Health Profile updated successfully", result);
        verify(healthProfileRepo, times(1)).save(existingProfile);
    }

    @Test
    void testUpdateHealthProfile_NotFound() {
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            healthProfileService.updateHealthProfile(1L, new HealthProfileCreateDto());
        });
    }

    @Test
    void testDeleteHealthProfile_Success() {
        HealthProfile profile = new HealthProfile();
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(profile);

        String result = healthProfileService.deleteHealthProfile(1L);

        assertEquals("Successfully deleted Health Profile with ID 1", result);
        verify(healthProfileRepo, times(1)).delete(profile);
    }

    @Test
    void testDeleteHealthProfile_NotFound() {
        when(healthProfileRepo.findByPatient_Id(1L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            healthProfileService.deleteHealthProfile(1L);
        });
    }

    @Test
    void testValidateHealthProfileDto_InvalidInputs() {
        HealthProfileCreateDto dto = new HealthProfileCreateDto();

        // No patient ID
        dto.setPatientId(null);
        assertThrows(InvalidInputException.class, () -> {
            healthProfileService.createHealthProfile(dto);
        });
    }
}
