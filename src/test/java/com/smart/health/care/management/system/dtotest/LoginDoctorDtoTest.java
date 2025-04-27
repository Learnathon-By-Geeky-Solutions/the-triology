package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.LoginDoctorDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginDoctorDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        // Act
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto();
        loginDoctorDto.setEmail(expectedEmail).setPassword(expectedPassword);

        // Assert
        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }

    @Test
    void testSettersWithFluentStyle() {
        // Arrange
        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        // Act
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto();
        loginDoctorDto.setEmail(expectedEmail).setPassword(expectedPassword);

        // Assert
        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }

    @Test
    void testChainingOfSetters() {
        // Arrange
        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        // Act
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto()
                .setEmail(expectedEmail)
                .setPassword(expectedPassword);

        // Assert
        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }
}
