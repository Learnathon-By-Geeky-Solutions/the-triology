package com.smart.health.care.management.system.modeltest;

import com.smart.health.care.management.system.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void testPatientFieldsAndMethods() {
        // Arrange
        Patient patient = new Patient();
        Long id = 1L;
        String name = "John Doe";
        String phoneNumber = "1234567890";
        String password = "securePassword";
        LocalDate dob = LocalDate.of(1995, 5, 5);

        // Act
        patient.setId(id)
                .setName(name)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .setDateOfBirth(dob);

        // Assert
        assertEquals(id, patient.getId());
        assertEquals(name, patient.getName());
        assertEquals(phoneNumber, patient.getPhoneNumber());
        assertEquals(password, patient.getPassword());
        assertEquals(dob, patient.getDateOfBirth());

        // Correct dynamic age calculation
        int expectedAge = Period.between(dob, LocalDate.now()).getYears();
        assertEquals(expectedAge, patient.getAge());

        assertEquals(phoneNumber, patient.getUsername());

        // Check UserDetails methods
        assertTrue(patient.isAccountNonExpired());
        assertTrue(patient.isAccountNonLocked());
        assertTrue(patient.isCredentialsNonExpired());
        assertTrue(patient.isEnabled());

        // Authorities should be empty
        assertTrue(patient.getAuthorities().isEmpty());
    }

    @Test
    void testCalculateAgeWhenDateOfBirthIsNull() {
        // Arrange
        Patient patient = new Patient();
        patient.setDateOfBirth(null);

        // Act
        int age = patient.getAge();

        // Assert
        assertEquals(0, age);
    }

    @Test
    void testUpdateAgeOnPersistOrUpdate() {
        // Arrange
        Patient patient = new Patient();
        LocalDate dob = LocalDate.of(2000, 1, 1);

        // Act
        patient.setDateOfBirth(dob);
        patient.updateAge();

        // Assert
        int expectedAge = Period.between(dob, LocalDate.now()).getYears();
        assertEquals(expectedAge, patient.getAge());
    }
}
