package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoctorCreateDtoTest {

    @Test
    void testDoctorCreateDto() {

        DoctorCreateDto doctorCreateDto = new DoctorCreateDto();
        String expectedDoctorName = "Dr. John Doe";
        String expectedDoctorSpeciality = "Cardiology";
        String expectedDoctorExperience = "10 years";
        String expectedDoctorEmail = "john.doe@example.com";
        String expectedDoctorPhone = "+1234567890";
        String expectedDoctorPassword = "password123";

        doctorCreateDto
                .setDoctorName(expectedDoctorName)
                .setDoctorSpeciality(expectedDoctorSpeciality)
                .setDoctorExperience(expectedDoctorExperience)
                .setDoctorEmail(expectedDoctorEmail)
                .setDoctorPhone(expectedDoctorPhone)
                .setDoctorPassword(expectedDoctorPassword);

        assertEquals(expectedDoctorName, doctorCreateDto.getDoctorName());
        assertEquals(expectedDoctorSpeciality, doctorCreateDto.getDoctorSpeciality());
        assertEquals(expectedDoctorExperience, doctorCreateDto.getDoctorExperience());
        assertEquals(expectedDoctorEmail, doctorCreateDto.getDoctorEmail());
        assertEquals(expectedDoctorPhone, doctorCreateDto.getDoctorPhone());
        assertEquals(expectedDoctorPassword, doctorCreateDto.getDoctorPassword());
    }
}
