package com.smart.health.care.management.system.repositorytest;

import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.AppointmentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AppointmentRepoTest {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Test
    void testSaveAppointment() {
        // Arrange
        // Create a Patient object
        Patient patient = new Patient();
        patient.setName("John Doe");  // Assuming Patient has a setName method

        // Create a Doctor object
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");  // Assuming Doctor has a setName method

        // Create an Appointment object
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);  // Set the Patient object here
        appointment.setDoctor(doctor);   // Set the Doctor object here
        appointment.setDate(LocalDate.of(2025, 4, 30));

        // Convert the time string "10:00 AM" to LocalTime
        String timeString = "10:00 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);

        appointment.setTime(time);

        // Act
        Appointment savedAppointment = appointmentRepo.save(appointment);

        // Assert
        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment.getId()).isGreaterThan(0);
        assertThat(savedAppointment.getPatient().getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindAppointmentById() {
        // Arrange
        // Create a Patient object
        Patient patient = new Patient();
        patient.setName("Jane Doe");  // Assuming Patient has a setName method

        // Create a Doctor object
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Brown");  // Assuming Doctor has a setName method

        // Create an Appointment object
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);  // Set the Patient object here
        appointment.setDoctor(doctor);   // Set the Doctor object here
        appointment.setDate(LocalDate.of(2025, 5, 1));

        // Convert the time string "02:00 PM" to LocalTime
        String timeString = "02:00 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);
        appointment.setTime(time);

        appointmentRepo.save(appointment);

        // Act
        Appointment foundAppointment = appointmentRepo.findById(appointment.getId()).orElse(null);

        // Assert
        assertThat(foundAppointment).isNotNull();
        assertThat(foundAppointment.getId()).isEqualTo(appointment.getId());
        assertThat(foundAppointment.getPatient().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testDeleteAppointment() {
        // Arrange
        // Create a Patient object
        Patient patient = new Patient();
        patient.setName("Alex Smith");  // Assuming Patient has a setName method

        // Create a Doctor object
        Doctor doctor = new Doctor();
        doctor.setName("Dr. White");  // Assuming Doctor has a setName method

        // Create an Appointment object
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);  // Set the Patient object here
        appointment.setDoctor(doctor);   // Set the Doctor object here
        appointment.setDate(LocalDate.of(2025, 6, 15));

        // Convert the time string "09:00 AM" to LocalTime
        String timeString = "09:00 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);
        appointment.setTime(time);

        Appointment savedAppointment = appointmentRepo.save(appointment);

        // Act
        appointmentRepo.delete(savedAppointment);

        // Assert
        Appointment deletedAppointment = appointmentRepo.findById(savedAppointment.getId()).orElse(null);
        assertThat(deletedAppointment).isNull();
    }
}
