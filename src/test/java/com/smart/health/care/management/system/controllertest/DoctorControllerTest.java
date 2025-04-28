package com.smart.health.care.management.system.controllertest;

import com.smart.health.care.management.system.controller.DoctorController;
import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.DoctorDto;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorControllerTest {

    private DoctorService doctorService;
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        doctorService = mock(DoctorService.class);
        doctorController = new DoctorController(doctorService);
    }

    @Test
    void testAddDoctor() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto();
        when(doctorService.addDoctor(doctorCreateDto)).thenReturn("DoctorId-1");

        ResponseEntity<CustomResponse<String>> response = doctorController.addDoctor(doctorCreateDto);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Doctor added successfully.", response.getBody().getResponseMessage());
        assertEquals("DoctorId-1", response.getBody().getData());

        verify(doctorService, times(1)).addDoctor(doctorCreateDto);
    }

    @Test
    void testGetDoctors() {
        DoctorDto dto1 = new DoctorDto(1, "Dr. Alice", "Cardiology", "5 years");
        DoctorDto dto2 = new DoctorDto(2, "Dr. Bob", "Neurology", "10 years");

        when(doctorService.getAllDoctors()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<CustomResponse<List<DoctorDto>>> response = doctorController.getDoctors();

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Fetched all doctors.", response.getBody().getResponseMessage());
        assertEquals(2, response.getBody().getData().size());

        verify(doctorService, times(1)).getAllDoctors();
    }

    @Test
    void testGetDoctorById() {
        DoctorDto dto = new DoctorDto(1, "Dr. Alice", "Cardiology", "5 years");

        when(doctorService.getDoctorById(1)).thenReturn(dto);

        ResponseEntity<CustomResponse<DoctorDto>> response = doctorController.getDoctorById(1);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Fetched Doctor by ID.", response.getBody().getResponseMessage());
        assertEquals(dto, response.getBody().getData());

        verify(doctorService, times(1)).getDoctorById(1);
    }

    @Test
    void testAuthenticatedUser() {
        Doctor mockDoctor = new Doctor();
        Authentication mockAuthentication = mock(Authentication.class);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(mock(org.springframework.security.core.context.SecurityContext.class));

            when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(mockAuthentication);
            when(mockAuthentication.getPrincipal()).thenReturn(mockDoctor);

            ResponseEntity<Doctor> response = doctorController.authenticatedUser();

            assertEquals(mockDoctor, response.getBody());
        }
    }

    @Test
    void testUpdateDoctor() {
        DoctorCreateDto updateDto = new DoctorCreateDto();
        when(doctorService.updateDoctor(1, updateDto)).thenReturn("Updated");

        ResponseEntity<CustomResponse<String>> response = doctorController.updateDoctor(1, updateDto);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Doctor updated successfully.", response.getBody().getResponseMessage());
        assertEquals("Updated", response.getBody().getData());

        verify(doctorService, times(1)).updateDoctor(1, updateDto);
    }

    @Test
    void testDeleteDoctorById() {
        when(doctorService.deleteDoctor(1)).thenReturn("Deleted");

        ResponseEntity<CustomResponse<String>> response = doctorController.deleteDoctorById(1);

        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Doctor deleted successfully.", response.getBody().getResponseMessage());
        assertEquals("Deleted", response.getBody().getData());

        verify(doctorService, times(1)).deleteDoctor(1);
    }
}