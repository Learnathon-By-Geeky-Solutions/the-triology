package com.smart.health.care.management.system.repositorytest;

import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.HealthProfileRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
class HealthProfileRepoTest {

    @Autowired
    private HealthProfileRepo healthProfileRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Test
    void testSaveHealthProfile() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setPhoneNumber("1234567890");
        patient.setEmail("john.doe@example.com");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 15));
        patientRepo.save(patient);

        HealthProfile healthProfile = new HealthProfile();
        healthProfile.setPatient(patient);  // Corrected: Use setPatient() instead of setPatientId()
        healthProfile.setHeight(175.5);
        healthProfile.setWeight(70.0);
        healthProfile.setBloodGroup("O+");
        healthProfile.setMedicalHistory("None");
        healthProfile.setConditions("Healthy");
        healthProfile.setAllergies(List.of("Peanuts", "Pollen"));
        healthProfileRepo.save(healthProfile);

        // Act
        HealthProfile savedHealthProfile = healthProfileRepo.findByPatientId(patient.getId());

        // Assert
        assertThat(savedHealthProfile).isNotNull();
        assertThat(savedHealthProfile.getPatient().getId()).isEqualTo(patient.getId());  // Verify patient ID correctly
        assertThat(savedHealthProfile.getBloodGroup()).isEqualTo("O+");
    }

    @Test
    void testFindByPatientId() {
        // Arrange
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setPhoneNumber("9876543210");
        patient.setEmail("jane.doe@example.com");
        patient.setDateOfBirth(LocalDate.of(1990, 8, 20));
        patientRepo.save(patient);

        HealthProfile healthProfile = new HealthProfile();
        healthProfile.setPatient(patient);  // Corrected: Use setPatient() instead of setPatientId()
        healthProfile.setHeight(160.0);
        healthProfile.setWeight(55.5);
        healthProfile.setBloodGroup("A+");
        healthProfile.setMedicalHistory("Asthma");
        healthProfile.setConditions("Chronic");
        healthProfile.setAllergies(List.of("Dust", "Pollen"));
        healthProfileRepo.save(healthProfile);

        // Act
        HealthProfile foundHealthProfile = healthProfileRepo.findByPatientId(patient.getId());

        // Assert
        assertThat(foundHealthProfile).isNotNull();
        assertThat(foundHealthProfile.getPatient().getId()).isEqualTo(patient.getId());  // Verify patient ID correctly
        assertThat(foundHealthProfile.getConditions()).isEqualTo("Chronic");
    }

    @Test
    void testFindByPatientId_NotFound() {
        // Act
        HealthProfile healthProfile = healthProfileRepo.findByPatientId(999);

        // Assert
        assertThat(healthProfile).isNull();
    }
}

