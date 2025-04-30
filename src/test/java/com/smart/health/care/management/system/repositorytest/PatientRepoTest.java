package com.smart.health.care.management.system.repositorytest;

import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PatientRepoTest {

    @Autowired
    private PatientRepo patientRepo;

    @Test
    void testSavePatient() {

        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setPhoneNumber("1234567890");
        patient.setEmail("john.doe@example.com");
        patient.setPassword("securepassword");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 15));

        Patient savedPatient = patientRepo.save(patient);

        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);
        assertThat(savedPatient.getPhoneNumber()).isEqualTo("1234567890");
    }

    @Test
    void testFindByPhoneNumber() {

        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setPhoneNumber("9876543210");
        patient.setEmail("jane.doe@example.com");
        patient.setPassword("securepassword");
        patient.setDateOfBirth(LocalDate.of(1990, 8, 20));
        patientRepo.save(patient);

        Optional<Patient> foundPatient = patientRepo.findByPhoneNumber("9876543210");

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getPhoneNumber()).isEqualTo("9876543210");
        assertThat(foundPatient.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testFindByPhoneNumber_NotFound() {

        Optional<Patient> foundPatient = patientRepo.findByPhoneNumber("0000000000");

        assertThat(foundPatient).isEmpty();
    }
}
