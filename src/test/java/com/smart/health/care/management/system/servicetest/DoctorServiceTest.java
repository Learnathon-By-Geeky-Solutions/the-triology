package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.DoctorDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.DoctorMapper;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.repository.DoctorRepo;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    private DoctorRepo doctorRepo;
    private DoctorMapper doctorMapper;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorRepo = mock(DoctorRepo.class);
        doctorMapper = mock(DoctorMapper.class);
        doctorService = new DoctorService(doctorRepo, doctorMapper);
    }

    @Test
    void testAddDoctor_Success() {
        DoctorCreateDto dto = new DoctorCreateDto();
        dto.setDoctorName("Dr. John");
        dto.setDoctorPhone("01700000000");
        dto.setDoctorEmail("dr.john@example.com");
        dto.setDoctorExperience("5 years");
        dto.setDoctorSpeciality("Cardiology");

        Doctor doctor = new Doctor();
        when(doctorMapper.toEntity(dto)).thenReturn(doctor);

        String result = doctorService.addDoctor(dto);

        assertEquals("Doctor added successfully", result);
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    void testAddDoctor_InvalidPhone() {
        DoctorCreateDto dto = new DoctorCreateDto();
        dto.setDoctorName("Dr. John");
        dto.setDoctorPhone("01700"); // Invalid phone
        dto.setDoctorEmail("dr.john@example.com");
        dto.setDoctorExperience("5 years");
        dto.setDoctorSpeciality("Cardiology");

        assertThrows(InvalidInputException.class, () -> doctorService.addDoctor(dto));
        verifyNoInteractions(doctorRepo);
    }

    @Test
    void testAddDoctor_InvalidEmail() {
        DoctorCreateDto dto = new DoctorCreateDto();
        dto.setDoctorName("Dr. John");
        dto.setDoctorPhone("01700000000");
        dto.setDoctorEmail("invalidemail"); // Invalid email
        dto.setDoctorExperience("5 years");
        dto.setDoctorSpeciality("Cardiology");

        assertThrows(BusinessLogicException.class, () -> doctorService.addDoctor(dto));
        verifyNoInteractions(doctorRepo);
    }

    @Test
    void testGetAllDoctors() {
        Doctor doctor = new Doctor();
        DoctorDto doctorDto = new DoctorDto(1, "Dr. John", "Cardiology", "5 years");

        when(doctorRepo.findAll()).thenReturn(List.of(doctor));
        when(doctorMapper.toDto(doctor)).thenReturn(doctorDto);

        List<DoctorDto> result = doctorService.getAllDoctors();

        assertEquals(1, result.size());
        assertEquals("Dr. John", result.get(0).getDocName());
        verify(doctorRepo, times(1)).findAll();
    }

    @Test
    void testGetAllDoctors_EmptyList() {
        when(doctorRepo.findAll()).thenReturn(Collections.emptyList());

        List<DoctorDto> result = doctorService.getAllDoctors();

        assertTrue(result.isEmpty());
        verify(doctorRepo, times(1)).findAll();
    }

    @Test
    void testGetDoctorById_Success() {
        Doctor doctor = new Doctor();
        DoctorDto doctorDto = new DoctorDto(1, "Dr. John", "Cardiology", "5 years");

        when(doctorRepo.findById(1)).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDto(doctor)).thenReturn(doctorDto);

        DoctorDto result = doctorService.getDoctorById(1);

        assertNotNull(result);
        assertEquals("Dr. John", result.getDocName());
    }

    @Test
    void testGetDoctorById_NotFound() {
        when(doctorRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.getDoctorById(1));
    }

    @Test
    void testUpdateDoctor_Success() {
        DoctorCreateDto dto = new DoctorCreateDto();
        dto.setDoctorName("Updated Name");
        dto.setDoctorPhone("01700000000");
        dto.setDoctorEmail("updated@example.com");
        dto.setDoctorExperience("10 years");
        dto.setDoctorSpeciality("Neurology");

        Doctor doctor = new Doctor();

        when(doctorRepo.findById(1)).thenReturn(Optional.of(doctor));

        String result = doctorService.updateDoctor(1, dto);

        assertEquals("Doctor updated successfully", result);
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    void testUpdateDoctor_NotFound() {
        DoctorCreateDto dto = new DoctorCreateDto();
        dto.setDoctorName("Updated Name");
        dto.setDoctorPhone("01700000000");
        dto.setDoctorEmail("updated@example.com");
        dto.setDoctorExperience("10 years");
        dto.setDoctorSpeciality("Neurology");

        when(doctorRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.updateDoctor(1, dto));
    }

    @Test
    void testDeleteDoctor_Success() {
        Doctor doctor = new Doctor();
        when(doctorRepo.findById(1)).thenReturn(Optional.of(doctor));

        String result = doctorService.deleteDoctor(1);

        assertEquals("Doctor deleted successfully", result);
        verify(doctorRepo, times(1)).delete(doctor);
    }

    @Test
    void testDeleteDoctor_NotFound() {
        when(doctorRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.deleteDoctor(1));
    }

    @Test
    void testGetTopExperiencedDoctors() {
        DoctorDto dto1 = new DoctorDto(1, "Dr. Alice", "Cardiology", "5 years");
        DoctorDto dto2 = new DoctorDto(2, "Dr. Bob", "Neurology", "10 years");

        when(doctorService.getTopExperiencedDoctors()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<CustomResponse<List<DoctorDto>>> response = (ResponseEntity<CustomResponse<List<DoctorDto>>>) doctorService.getTopExperiencedDoctors();

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("List of the popular doctors : ", response.getBody().getResponseMessage());
        assertEquals(2, response.getBody().getData().size());

        verify(doctorService, times(1)).getTopExperiencedDoctors();
    }
}