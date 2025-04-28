package com.smart.health.care.management.system.modeltest;

import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    void testAppointmentSettersAndGetters() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L).setName("John Doe");

        Doctor doctor = new Doctor();
        doctor.setId(1).setName("Dr. Smith");

        LocalDate date = LocalDate.of(2025, 5, 5);
        LocalTime time = LocalTime.of(10, 30);

        Appointment appointment = new Appointment();

        // Act
        appointment.setId(100L);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(date);
        appointment.setTime(time);

        // Assert
        assertEquals(100L, appointment.getId());
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(date, appointment.getDate());
        assertEquals(time, appointment.getTime());
    }

    @Test
    void testAppointmentParameterizedConstructor() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(2L).setName("Jane Doe");

        Doctor doctor = new Doctor();
        doctor.setId(2).setName("Dr. Brown");

        LocalDate date = LocalDate.of(2025, 12, 25);
        LocalTime time = LocalTime.of(9, 0);

        // Act
        Appointment appointment = new Appointment(200L, patient, doctor, date, time);

        // Assert
        assertEquals(200L, appointment.getId());
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals(date, appointment.getDate());
        assertEquals(time, appointment.getTime());
    }
}
