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
        // Arrange
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setPhoneNumber("1234567890");
        patient.setEmail("john.doe@example.com");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 15));

        // Act
        Patient savedPatient = patientRepo.save(patient);

        // Assert
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);  // Ensure the patient has been saved with a generated ID
        assertThat(savedPatient.getPhoneNumber()).isEqualTo("1234567890");
    }

    @Test
    void testFindByPhoneNumber() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setPhoneNumber("9876543210");
        patient.setEmail("jane.doe@example.com");
        patient.setDateOfBirth(LocalDate.of(1990, 8, 20));
        patientRepo.save(patient);

        // Act
        Optional<Patient> foundPatient = patientRepo.findByPhoneNumber("9876543210");

        // Assert
        assertThat(foundPatient).isPresent();  // Ensure the patient is found
        assertThat(foundPatient.get().getPhoneNumber()).isEqualTo("9876543210");
        assertThat(foundPatient.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testFindByPhoneNumber_NotFound() {
        // Act
        Optional<Patient> foundPatient = patientRepo.findByPhoneNumber("0000000000");

        // Assert
        assertThat(foundPatient).isEmpty();  // Ensure no patient is found
    }
}
