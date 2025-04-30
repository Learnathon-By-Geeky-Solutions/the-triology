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

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        doctor.setEmail("dr.smith@example.com");
        doctor.setPhone("0123456789");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setExperience("10 years");


        Doctor savedDoctor = doctorRepo.save(doctor);

        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor.getId()).isGreaterThan(0);
        assertThat(savedDoctor.getEmail()).isEqualTo("dr.smith@example.com");
    }

    @Test
    void testFindDoctorById() {

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Brown");
        doctor.setEmail("dr.brown@example.com");
        doctor.setSpecialty("Neurology");
        doctor.setExperience("8 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        doctorRepo.save(doctor);

        Doctor foundDoctor = doctorRepo.findById(doctor.getId()).orElse(null);

        assertThat(foundDoctor).isNotNull();
        assertThat(foundDoctor.getId()).isEqualTo(doctor.getId());
        assertThat(foundDoctor.getName()).isEqualTo("Dr. Brown");
    }

    @Test
    void testFindDoctorByEmail() {

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Green");
        doctor.setEmail("dr.green@example.com");
        doctor.setSpecialty("Pediatrics");
        doctor.setExperience("5 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        doctorRepo.save(doctor);

        Doctor foundDoctor = doctorRepo.findByEmail("dr.green@example.com").orElse(null);

        assertThat(foundDoctor).isNotNull();
        assertThat(foundDoctor.getEmail()).isEqualTo("dr.green@example.com");
        assertThat(foundDoctor.getName()).isEqualTo("Dr. Green");
    }

    @Test
    void testDeleteDoctor() {

        Doctor doctor = new Doctor();
        doctor.setName("Dr. White");
        doctor.setEmail("dr.white@example.com");
        doctor.setSpecialty("Orthopedics");
        doctor.setExperience("15 years");
        doctor.setPassword("securepassword");
        doctor.setSpecialty("Cardiology");
        doctor.setPhone("0123456789");
        Doctor savedDoctor = doctorRepo.save(doctor);

        doctorRepo.delete(savedDoctor);

        Doctor deletedDoctor = doctorRepo.findById(savedDoctor.getId()).orElse(null);
        assertThat(deletedDoctor).isNull();
    }
}
