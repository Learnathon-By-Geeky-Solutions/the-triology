package com.smart.health.care.management.system.controllertest;

import com.smart.health.care.management.system.controller.HealthProfileController;
import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import com.smart.health.care.management.system.dto.HealthProfileDto;
import com.smart.health.care.management.system.service.HealthProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthProfileControllerTest {

    private HealthProfileService healthProfileService;
    private HealthProfileController healthProfileController;

    @BeforeEach
    void setUp() {
        healthProfileService = mock(HealthProfileService.class);
        healthProfileController = new HealthProfileController(healthProfileService);
    }

    @Test
    void testGetHealthProfileByPatientId() {
        HealthProfileDto mockProfile = HealthProfileDto.builder()
                .id(1L)
                .patientId(1L)
                .height(170.5)
                .weight(70.0)
                .bloodGroup("O+")
                .allergies(List.of("Pollen"))
                .medicalHistory("No history")
                .conditions("Healthy")
                .build();

        when(healthProfileService.getHealthProfileByPatientId(1L)).thenReturn(mockProfile);

        ResponseEntity<HealthProfileDto> response = healthProfileController.getHealthProfile(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProfile, response.getBody());

        verify(healthProfileService, times(1)).getHealthProfileByPatientId(1L);
    }

    @Test
    void testGetAllHealthProfiles_WhenProfilesExist() {
        HealthProfileDto profile1 = HealthProfileDto.builder()
                .id(1L)
                .patientId(1L)
                .height(170.0)
                .weight(65.0)
                .bloodGroup("A+")
                .allergies(List.of("Dust"))
                .medicalHistory("None")
                .conditions("Normal")
                .build();

        HealthProfileDto profile2 = HealthProfileDto.builder()
                .id(2L)
                .patientId(2L)
                .height(180.0)
                .weight(80.0)
                .bloodGroup("B+")
                .allergies(List.of("Pollen"))
                .medicalHistory("Asthma")
                .conditions("Needs monitoring")
                .build();

        when(healthProfileService.getAllHealthProfiles()).thenReturn(List.of(profile1, profile2));

        ResponseEntity<List<HealthProfileDto>> response = healthProfileController.getAllHealthProfiles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(healthProfileService, times(1)).getAllHealthProfiles();
    }

    @Test
    void testGetAllHealthProfiles_WhenNoProfilesExist() {
        when(healthProfileService.getAllHealthProfiles()).thenReturn(List.of());

        ResponseEntity<List<HealthProfileDto>> response = healthProfileController.getAllHealthProfiles();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(healthProfileService, times(1)).getAllHealthProfiles();
    }

    @Test
    void testSaveHealthProfile() {
        HealthProfileCreateDto createDto = new HealthProfileCreateDto();
        HealthProfileDto savedDto = HealthProfileDto.builder()
                .id(1L)
                .patientId(1L)
                .height(170.5)
                .weight(70.0)
                .bloodGroup("O+")
                .allergies(List.of("Pollen"))
                .medicalHistory("No history")
                .conditions("Healthy")
                .build();

        when(healthProfileService.createHealthProfile(createDto)).thenReturn(savedDto);

        ResponseEntity<HealthProfileDto> response = healthProfileController.saveHealthProfile(createDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedDto, response.getBody());

        verify(healthProfileService, times(1)).createHealthProfile(createDto);
    }

    @Test
    void testUpdateHealthProfile() {
        HealthProfileCreateDto updateDto = new HealthProfileCreateDto();
        when(healthProfileService.updateHealthProfile(1L, updateDto)).thenReturn("Updated Successfully");

        ResponseEntity<String> response = healthProfileController.updateHealthProfile(1L, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Successfully", response.getBody());

        verify(healthProfileService, times(1)).updateHealthProfile(1L, updateDto);
    }

    @Test
    void testDeleteHealthProfile() {
        when(healthProfileService.deleteHealthProfile(1L)).thenReturn("Deleted Successfully");

        ResponseEntity<Void> response = healthProfileController.deleteHealthProfile(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(healthProfileService, times(1)).deleteHealthProfile(1L);
    }
}
