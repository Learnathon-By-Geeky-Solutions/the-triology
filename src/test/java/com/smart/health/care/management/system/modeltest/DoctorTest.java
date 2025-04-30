package com.smart.health.care.management.system.modeltest;

import com.smart.health.care.management.system.model.Doctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void testDoctorFieldsAndMethods() {

        Doctor doctor = new Doctor();
        int id = 1;
        String name = "Dr. John Doe";
        String specialty = "Cardiology";
        String experience = "10 years";
        String email = "johndoe@example.com";
        String phone = "1234567890";
        String password = "securePassword";

        doctor.setId(id)
                .setName(name)
                .setSpecialty(specialty)
                .setExperience(experience)
                .setEmail(email)
                .setPhone(phone)
                .setPassword(password);


        assertEquals(id, doctor.getId());
        assertEquals(name, doctor.getName());
        assertEquals(specialty, doctor.getSpecialty());
        assertEquals(experience, doctor.getExperience());
        assertEquals(email, doctor.getEmail());
        assertEquals(phone, doctor.getPhone());
        assertEquals(password, doctor.getPassword());

        assertEquals(email, doctor.getUsername()); // username is email
        assertEquals(password, doctor.getPassword());
        assertTrue(doctor.isAccountNonExpired());
        assertTrue(doctor.isAccountNonLocked());
        assertTrue(doctor.isCredentialsNonExpired());
        assertTrue(doctor.isEnabled());
        assertTrue(doctor.getAuthorities().isEmpty()); // No authorities assigned
    }

    @Test
    void testDoctorParameterizedConstructor() {

        String name = "Dr. Jane Smith";
        String specialty = "Neurology";
        String experience = "8 years";
        String email = "janesmith@example.com";
        String phone = "0987654321";
        String password = "anotherPassword";

        Doctor doctor = new Doctor(name, specialty, experience, email, phone, password);

        assertEquals(name, doctor.getName());
        assertEquals(specialty, doctor.getSpecialty());
        assertEquals(experience, doctor.getExperience());
        assertEquals(email, doctor.getEmail());
        assertEquals(phone, doctor.getPhone());
        assertEquals(password, doctor.getPassword());
    }
}
