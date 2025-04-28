package com.smart.health.care.management.system.controllertest;

import com.smart.health.care.management.system.controller.PatientController;
import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    private PatientService patientService;
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        patientService = mock(PatientService.class);
        patientController = new PatientController(patientService);
    }

    @Test
    void testAddPatient() {
        PatientCreateDto createDto = new PatientCreateDto();
        when(patientService.addPatient(createDto)).thenReturn("PatientId-1");

        ResponseEntity<CustomResponse<String>> response = patientController.addPatient(createDto);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Patient added successfully.", response.getBody().getResponseMessage());
        assertEquals("PatientId-1", response.getBody().getData());

        verify(patientService, times(1)).addPatient(createDto);
    }

    @Test
    void testAuthenticatedUser() {
        Patient mockPatient = new Patient();
        Authentication mockAuthentication = mock(Authentication.class);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(mock(org.springframework.security.core.context.SecurityContext.class));

            when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(mockAuthentication);
            when(mockAuthentication.getPrincipal()).thenReturn(mockPatient);

            ResponseEntity<Patient> response = patientController.authenticatedUser();

            assertEquals(mockPatient, response.getBody());
        }
    }

    @Test
    void testGetPatients() {
        PatientDto dto1 = new PatientDto(1L, "Alice", "01710000000");
        PatientDto dto2 = new PatientDto(2L, "Bob", "01810000000");

        when(patientService.getAllPatients()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<CustomResponse<List<PatientDto>>> response = patientController.getPatients();

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Fetched all patients.", response.getBody().getResponseMessage());
        assertEquals(2, response.getBody().getData().size());

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testGetPatientById() {
        PatientDto dto = new PatientDto(1L, "Alice", "01710000000");

        when(patientService.getPatientById(1L)).thenReturn(dto);

        ResponseEntity<CustomResponse<PatientDto>> response = patientController.getPatient(1L);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Fetched patient by ID.", response.getBody().getResponseMessage());
        assertEquals(dto, response.getBody().getData());

        verify(patientService, times(1)).getPatientById(1L);
    }

    @Test
    void testUpdatePatient() {
        PatientCreateDto updateDto = new PatientCreateDto();
        when(patientService.updatePatient(1L, updateDto)).thenReturn("Updated Successfully");

        ResponseEntity<CustomResponse<String>> response = patientController.updatePatient(1L, updateDto);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Patient updated successfully.", response.getBody().getResponseMessage());
        assertEquals("Updated Successfully", response.getBody().getData());

        verify(patientService, times(1)).updatePatient(1L, updateDto);
    }

    @Test
    void testDeletePatient() {
        when(patientService.deletePatient(1L)).thenReturn("Deleted Successfully");

        ResponseEntity<CustomResponse<String>> response = patientController.deletePatient(1L);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Patient deleted successfully.", response.getBody().getResponseMessage());
        assertEquals("Deleted Successfully", response.getBody().getData());

        verify(patientService, times(1)).deletePatient(1L);
    }
}