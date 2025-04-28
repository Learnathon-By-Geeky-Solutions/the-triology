package com.smart.health.care.management.system.repositorytest;

import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.HealthProfileRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HealthProfileRepoTest {

    @Autowired
    private HealthProfileRepo healthProfileRepo;

    @Autowired
    private PatientRepo patientRepo;

    private Patient testPatient;
    private HealthProfile testHealthProfile;

    @BeforeEach
    void setUp() {
        // Create a Patient entity for testing
        testPatient = new Patient();
        testPatient.setName("John Doe");
        testPatient.setPhoneNumber("1234567890");
        testPatient.setEmail("john.doe@example.com");
        testPatient.setDateOfBirth(LocalDate.of(1985, 5, 15));
        testPatient.setPhoneNumber("0123456789");
        testPatient.setPassword("somePassword");
        patientRepo.save(testPatient);

        // Create a HealthProfile associated with the testPatient
        testHealthProfile = new HealthProfile();
        testHealthProfile.setPatient(testPatient);
        testHealthProfile.setHeight(175.5);
        testHealthProfile.setWeight(70.0);
        testHealthProfile.setBloodGroup("O+");
        testHealthProfile.setMedicalHistory("None");
        testHealthProfile.setConditions("Healthy");
        healthProfileRepo.save(testHealthProfile);
    }

    @Test
    void testFindByPatientId() {
        // Act
        HealthProfile foundHealthProfile = healthProfileRepo.findByPatient_Id(testPatient.getId());

        // Assert
        assertThat(foundHealthProfile).isNotNull();
        assertThat(foundHealthProfile.getPatient()).isEqualTo(testPatient);
        assertThat(foundHealthProfile.getHeight()).isEqualTo(175.5);
        assertThat(foundHealthProfile.getWeight()).isEqualTo(70.0);
        assertThat(foundHealthProfile.getBloodGroup()).isEqualTo("O+");
    }

    @Test
    void testFindByPatientIdNotFound() {
        // Act
        HealthProfile foundHealthProfile = healthProfileRepo.findByPatient_Id(999L); // Invalid ID

        // Assert
        assertThat(foundHealthProfile).isNull(); // Should return null
    }

    @Test
    void testSaveHealthProfile() {
        // Arrange
        HealthProfile newHealthProfile = new HealthProfile();
        newHealthProfile.setPatient(testPatient);
        newHealthProfile.setHeight(180.0);
        newHealthProfile.setWeight(75.0);
        newHealthProfile.setBloodGroup("A+");
        newHealthProfile.setMedicalHistory("None");
        newHealthProfile.setConditions("Healthy");

        // Act
        HealthProfile savedHealthProfile = healthProfileRepo.save(newHealthProfile);

        // Assert
        assertThat(savedHealthProfile).isNotNull();
        assertThat(savedHealthProfile.getId()).isGreaterThan(0);
        assertThat(savedHealthProfile.getPatient()).isEqualTo(testPatient);
        assertThat(savedHealthProfile.getHeight()).isEqualTo(180.0);
        assertThat(savedHealthProfile.getWeight()).isEqualTo(75.0);
        assertThat(savedHealthProfile.getBloodGroup()).isEqualTo("A+");
    }

    @Test
    void testDeleteHealthProfile() {
        // Act
        healthProfileRepo.delete(testHealthProfile);

        // Assert
        HealthProfile deletedHealthProfile = healthProfileRepo.findByPatient_Id(testPatient.getId());
        assertThat(deletedHealthProfile).isNull(); // Should return null after deletion
    }
}
