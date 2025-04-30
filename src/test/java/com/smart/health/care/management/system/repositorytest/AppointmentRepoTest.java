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

        Patient patient = new Patient();
        patient.setName("John Doe");


        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.of(2025, 4, 30));

        String timeString = "10:00 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);

        appointment.setTime(time);

        Appointment savedAppointment = appointmentRepo.save(appointment);

        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment.getId()).isGreaterThan(0);
        assertThat(savedAppointment.getPatient().getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindAppointmentById() {

        Patient patient = new Patient();
        patient.setName("Jane Doe");

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Brown");

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.of(2025, 5, 1));

        String timeString = "02:00 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);
        appointment.setTime(time);

        appointmentRepo.save(appointment);

        Appointment foundAppointment = appointmentRepo.findById(appointment.getId()).orElse(null);

        assertThat(foundAppointment).isNotNull();
        assertThat(foundAppointment.getId()).isEqualTo(appointment.getId());
        assertThat(foundAppointment.getPatient().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testDeleteAppointment() {

        Patient patient = new Patient();
        patient.setName("Alex Smith");

        Doctor doctor = new Doctor();
        doctor.setName("Dr. White");

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(LocalDate.of(2025, 6, 15));

        String timeString = "09:00 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime time = LocalTime.parse(timeString, formatter);
        appointment.setTime(time);

        Appointment savedAppointment = appointmentRepo.save(appointment);

        appointmentRepo.delete(savedAppointment);

        Appointment deletedAppointment = appointmentRepo.findById(savedAppointment.getId()).orElse(null);
        assertThat(deletedAppointment).isNull();
    }
}
