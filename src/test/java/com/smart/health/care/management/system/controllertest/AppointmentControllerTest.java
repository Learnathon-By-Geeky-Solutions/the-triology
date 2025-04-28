package com.smart.health.care.management.system.controllertest;

import com.smart.health.care.management.system.controller.AppointmentController;
import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import com.smart.health.care.management.system.dto.AppointmentDto;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentControllerTest {

    private AppointmentService appointmentService;
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        appointmentService = mock(AppointmentService.class);
        appointmentController = new AppointmentController(appointmentService);
    }

    @Test
    void testAddAppointment() {
        AppointmentCreateDto createDto = new AppointmentCreateDto();
        when(appointmentService.addAppointment(createDto)).thenReturn("AppointmentId-1");

        ResponseEntity<CustomResponse<String>> response = appointmentController.addAppointment(createDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("S0000", response.getBody().getResponseCode());
        assertEquals("Appointment added successfully.", response.getBody().getResponseMessage());
        assertEquals("AppointmentId-1", response.getBody().getData());

        verify(appointmentService, times(1)).addAppointment(createDto);
    }

    @Test
    void testGetAppointments() {
        AppointmentDto dto1 = new AppointmentDto();
        AppointmentDto dto2 = new AppointmentDto();
        when(appointmentService.getAllAppointments()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<CustomResponse<List<AppointmentDto>>> response = appointmentController.getAppointments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getData().size());
        assertEquals("Fetched all appointments.", response.getBody().getResponseMessage());

        verify(appointmentService, times(1)).getAllAppointments();
    }

    @Test
    void testGetAppointmentById() {
        AppointmentDto dto = new AppointmentDto();
        when(appointmentService.getAppointmentById(1L)).thenReturn(dto);

        ResponseEntity<CustomResponse<AppointmentDto>> response = appointmentController.getAppointmentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody().getData());
        assertEquals("Fetched appointment by ID.", response.getBody().getResponseMessage());

        verify(appointmentService, times(1)).getAppointmentById(1L);
    }

    @Test
    void testUpdateAppointment() {
        AppointmentCreateDto updateDto = new AppointmentCreateDto();
        when(appointmentService.updateAppointment(1L, updateDto)).thenReturn("Updated");

        ResponseEntity<CustomResponse<String>> response = appointmentController.updateAppointment(1L, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment updated successfully.", response.getBody().getResponseMessage());
        assertEquals("Updated", response.getBody().getData());

        verify(appointmentService, times(1)).updateAppointment(1L, updateDto);
    }

    @Test
    void testDeleteAppointment() {
        when(appointmentService.deleteAppointment(1L)).thenReturn("Deleted");

        ResponseEntity<CustomResponse<String>> response = appointmentController.deleteAppointment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment deleted successfully.", response.getBody().getResponseMessage());
        assertEquals("Deleted", response.getBody().getData());

        verify(appointmentService, times(1)).deleteAppointment(1L);
    }
}