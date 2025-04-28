package com.smart.health.care.management.system.repositorytest;

import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.repository.DoctorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DoctorRepoTest {

    @Autowired
    private DoctorRepo doctorRepo;

    @Test
    void testSaveDoctor() {
        // Arrange: Create and set values for a Doctor object
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setEmail("dr.smith@example.com");
        doctor.setPhone("0123456789");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setExperience("10 years");

        // Act: Save the doctor object to the repository
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert: Check that the saved doctor is not null and the fields are correctly saved
        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor.getId()).isGreaterThan(0);  // Verify that the doctor has a non-zero ID
        assertThat(savedDoctor.getEmail()).isEqualTo("dr.smith@example.com");  // Verify email
    }

    @Test
    void testFindDoctorById() {
        // Arrange: Create a doctor object and save it
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Brown");
        doctor.setEmail("dr.brown@example.com");
        doctor.setSpecialty("Neurology");
        doctor.setExperience("8 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        doctorRepo.save(doctor);

        // Act: Retrieve the doctor by ID
        Doctor foundDoctor = doctorRepo.findById(doctor.getId()).orElse(null);

        // Assert: Check that the doctor is found and the details match
        assertThat(foundDoctor).isNotNull();  // Check if doctor is found
        assertThat(foundDoctor.getId()).isEqualTo(doctor.getId());  // Verify ID
        assertThat(foundDoctor.getName()).isEqualTo("Dr. Brown");  // Verify name
    }

    @Test
    void testFindDoctorByEmail() {
        // Arrange: Create a doctor object and save it
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Green");
        doctor.setEmail("dr.green@example.com");
        doctor.setSpecialty("Pediatrics");
        doctor.setExperience("5 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        doctorRepo.save(doctor);

        // Act: Retrieve the doctor by email
        Doctor foundDoctor = doctorRepo.findByEmail("dr.green@example.com").orElse(null);

        // Assert: Check that the doctor is found and the details match
        assertThat(foundDoctor).isNotNull();  // Check if doctor is found by email
        assertThat(foundDoctor.getEmail()).isEqualTo("dr.green@example.com");  // Verify email
        assertThat(foundDoctor.getName()).isEqualTo("Dr. Green");  // Verify name
    }

    @Test
    void testDeleteDoctor() {
        // Arrange: Create a doctor object and save it
        Doctor doctor = new Doctor();
        doctor.setName("Dr. White");
        doctor.setEmail("dr.white@example.com");
        doctor.setSpecialty("Orthopedics");
        doctor.setExperience("15 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Act: Delete the saved doctor object
        doctorRepo.delete(savedDoctor);

        // Assert: Check if the doctor is deleted by trying to find it again
        Doctor deletedDoctor = doctorRepo.findById(savedDoctor.getId()).orElse(null);
        assertThat(deletedDoctor).isNull();  // Verify the doctor is deleted
    }
}
