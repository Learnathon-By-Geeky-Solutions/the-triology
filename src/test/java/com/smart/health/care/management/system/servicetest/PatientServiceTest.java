package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.PatientMapper;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.PatientRepo;
import com.smart.health.care.management.system.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private PatientRepo patientRepo;
    private PatientMapper patientMapper;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        patientRepo = mock(PatientRepo.class);
        patientMapper = mock(PatientMapper.class);
        patientService = new PatientService(patientRepo, patientMapper);
    }

    @Test
    void testAddPatient_Success() {
        PatientCreateDto createDto = new PatientCreateDto();
        createDto.setName("John Doe");
        createDto.setPhoneNumber("01234567890");
        createDto.setDateOfBirth(LocalDate.of(2000, 1, 1));

        Patient patient = new Patient();
        when(patientMapper.toEntity(createDto)).thenReturn(patient);
        when(patientRepo.save(patient)).thenReturn(patient);

        String result = patientService.addPatient(createDto);

        assertEquals("Patient added", result);
        verify(patientRepo).save(patient);
    }

    @Test
    void testAddPatient_InvalidPhoneNumber() {
        PatientCreateDto createDto = new PatientCreateDto();
        createDto.setName("John Doe");
        createDto.setPhoneNumber("01234"); // too short
        createDto.setDateOfBirth(LocalDate.of(2000, 1, 1));

        assertThrows(InvalidInputException.class, () -> patientService.addPatient(createDto));
    }

    @Test
    void testAddPatient_FutureDateOfBirth() {
        PatientCreateDto createDto = new PatientCreateDto();
        createDto.setName("John Doe");
        createDto.setPhoneNumber("01234567890");
        createDto.setDateOfBirth(LocalDate.now().plusDays(1)); // future date

        assertThrows(BusinessLogicException.class, () -> patientService.addPatient(createDto));
    }

    @Test
    void testGetAllPatients() {
        when(patientRepo.findAll()).thenReturn(Collections.emptyList());

        assertTrue(patientService.getAllPatients().isEmpty());
    }

    @Test
    void testGetPatientById_Success() {
        Patient patient = new Patient();
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));

        PatientDto dto = new PatientDto(1L, "John Doe", "01234567890");
        when(patientMapper.toDto(patient)).thenReturn(dto);

        PatientDto result = patientService.getPatientById(1L);

        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(1L));
    }

    @Test
    void testUpdatePatient_Success() {
        Patient existingPatient = new Patient();
        when(patientRepo.findById(1L)).thenReturn(Optional.of(existingPatient));

        PatientCreateDto createDto = new PatientCreateDto();
        createDto.setName("Updated Name");
        createDto.setPhoneNumber("09876543210");
        createDto.setDateOfBirth(LocalDate.of(1995, 5, 5));

        String result = patientService.updatePatient(1L, createDto);

        assertEquals("Patient updated", result);
        verify(patientRepo).save(existingPatient);
    }

    @Test
    void testDeletePatient_Success() {
        Patient patient = new Patient();
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));

        String result = patientService.deletePatient(1L);

        assertEquals("Patient deleted", result);
        verify(patientRepo).delete(patient);
    }

    @Test
    void testDeletePatient_NotFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(1L));
    }

    @Test
    void testGetPatientEntityById_Success() {
        Patient patient = new Patient();
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientEntityById(1L);

        assertNotNull(result);
    }

    @Test
    void testGetPatientEntityById_NotFound() {
        when(patientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientEntityById(1L));
    }
}