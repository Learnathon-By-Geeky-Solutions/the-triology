package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import com.smart.health.care.management.system.dto.AppointmentDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.AppointmentMapper;
import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.AppointmentRepo;
import com.smart.health.care.management.system.repository.DoctorRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import com.smart.health.care.management.system.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    private AppointmentRepo appointmentRepo;
    private PatientRepo patientRepo;
    private DoctorRepo doctorRepo;
    private AppointmentMapper appointmentMapper;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentRepo = mock(AppointmentRepo.class);
        patientRepo = mock(PatientRepo.class);
        doctorRepo = mock(DoctorRepo.class);
        appointmentMapper = mock(AppointmentMapper.class);
        appointmentService = new AppointmentService(appointmentRepo, patientRepo, doctorRepo, appointmentMapper);
    }

    @Test
    void testAddAppointment_Success() {
        AppointmentCreateDto dto = new AppointmentCreateDto();
        dto.setPatientId(1);
        dto.setDoctorId(2);
        dto.setDate(LocalDate.now().plusDays(1));
        dto.setTime(LocalTime.of(10, 0));

        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment();

        when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
        when(doctorRepo.findById(2)).thenReturn(Optional.of(doctor));
        when(appointmentMapper.toEntity(dto, patient, doctor)).thenReturn(appointment);

        String result = appointmentService.addAppointment(dto);

        assertEquals("Appointment added", result);
        verify(appointmentRepo, times(1)).save(appointment);
    }

    @Test
    void testAddAppointment_InvalidDate() {
        AppointmentCreateDto dto = new AppointmentCreateDto();
        dto.setPatientId(1);
        dto.setDoctorId(2);
        dto.setDate(LocalDate.now().minusDays(1)); // Past date
        dto.setTime(LocalTime.of(10, 0));

        assertThrows(BusinessLogicException.class, () -> appointmentService.addAppointment(dto));
        verifyNoInteractions(patientRepo, doctorRepo, appointmentMapper, appointmentRepo);
    }

    @Test
    void testAddAppointment_NullTime() {
        AppointmentCreateDto dto = new AppointmentCreateDto();
        dto.setPatientId(1);
        dto.setDoctorId(2);
        dto.setDate(LocalDate.now().plusDays(1));
        dto.setTime(null);

        assertThrows(InvalidInputException.class, () -> appointmentService.addAppointment(dto));
        verifyNoInteractions(patientRepo, doctorRepo, appointmentMapper, appointmentRepo);
    }

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();
        AppointmentDto dto = new AppointmentDto();

        when(appointmentRepo.findAll()).thenReturn(List.of(appointment));
        when(appointmentMapper.toDto(appointment)).thenReturn(dto);

        List<AppointmentDto> result = appointmentService.getAllAppointments();

        assertEquals(1, result.size());
        verify(appointmentRepo, times(1)).findAll();
    }

    @Test
    void testGetAppointmentById_Success() {
        Appointment appointment = new Appointment();
        AppointmentDto dto = new AppointmentDto();

        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentMapper.toDto(appointment)).thenReturn(dto);

        AppointmentDto result = appointmentService.getAppointmentById(1L);

        assertNotNull(result);
        verify(appointmentRepo, times(1)).findById(1L);
    }

    @Test
    void testGetAppointmentById_NotFound() {
        when(appointmentRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> appointmentService.getAppointmentById(1L));
    }

    @Test
    void testUpdateAppointment_Success() {
        AppointmentCreateDto dto = new AppointmentCreateDto();
        dto.setPatientId(1);
        dto.setDoctorId(2);
        dto.setDate(LocalDate.now().plusDays(1));
        dto.setTime(LocalTime.of(10, 0));

        Appointment existingAppointment = new Appointment();
        Patient patient = new Patient();
        Doctor doctor = new Doctor();

        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
        when(doctorRepo.findById(2)).thenReturn(Optional.of(doctor));

        String result = appointmentService.updateAppointment(1L, dto);

        assertEquals("Appointment updated", result);
        verify(appointmentRepo, times(1)).save(existingAppointment);
    }

    @Test
    void testDeleteAppointment_Success() {
        Appointment appointment = new Appointment();

        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(appointment));

        String result = appointmentService.deleteAppointment(1L);

        assertEquals("Appointment deleted", result);
        verify(appointmentRepo, times(1)).delete(appointment);
    }

    @Test
    void testDeleteAppointment_NotFound() {
        when(appointmentRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> appointmentService.deleteAppointment(1L));
    }
}