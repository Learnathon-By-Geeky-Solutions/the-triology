package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.LoginDoctorDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginDoctorDtoTest {

    @Test
    void testConstructorAndGetters() {

        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        LoginDoctorDto loginDoctorDto = new LoginDoctorDto();
        loginDoctorDto.setEmail(expectedEmail).setPassword(expectedPassword);

        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }

    @Test
    void testSettersWithFluentStyle() {
        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        LoginDoctorDto loginDoctorDto = new LoginDoctorDto();
        loginDoctorDto.setEmail(expectedEmail).setPassword(expectedPassword);

        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }

    @Test
    void testChainingOfSetters() {
        String expectedEmail = "doctor@example.com";
        String expectedPassword = "password123";

        LoginDoctorDto loginDoctorDto = new LoginDoctorDto()
                .setEmail(expectedEmail)
                .setPassword(expectedPassword);

        assertEquals(expectedEmail, loginDoctorDto.getEmail());
        assertEquals(expectedPassword, loginDoctorDto.getPassword());
    }
}
