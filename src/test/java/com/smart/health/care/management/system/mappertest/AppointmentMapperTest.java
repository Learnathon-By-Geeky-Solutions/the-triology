package com.smart.health.care.management.system.mappertest;

import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import com.smart.health.care.management.system.dto.AppointmentDto;
import com.smart.health.care.management.system.mapper.AppointmentMapper;
import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentMapperTest {

    private AppointmentMapper appointmentMapper;

    @BeforeEach
    void setUp() {
        appointmentMapper = new AppointmentMapper();
    }

    @Test
    void testToDto() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("John Doe");

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.of(2025, 5, 5));
        appointment.setTime(LocalTime.of(10, 30));

        // Act
        AppointmentDto dto = appointmentMapper.toDto(appointment);

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("John Doe", dto.getPatientName());
        assertEquals("Dr. Smith", dto.getDoctorName());
        assertEquals(LocalDate.of(2025, 5, 5), dto.getDate());
        assertEquals(LocalTime.of(10, 30), dto.getTime());
    }

    @Test
    void testToEntity() {
        // Arrange
        AppointmentCreateDto dto = new AppointmentCreateDto();
        dto.setDate(LocalDate.of(2025, 6, 10));
        dto.setTime(LocalTime.of(14, 0));

        Patient patient = new Patient();
        patient.setName("Jane Doe");

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Taylor");

        // Act
        Appointment appointment = appointmentMapper.toEntity(dto, patient, doctor);

        // Assert
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(LocalDate.of(2025, 6, 10), appointment.getDate());
        assertEquals(LocalTime.of(14, 0), appointment.getTime());
    }
}
