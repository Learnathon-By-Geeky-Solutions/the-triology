package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.DoctorDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoctorDtoTest {

    @Test
    void testConstructorAndGetters() {

        int expectedId = 1;
        String expectedDocName = "Dr. Smith";
        String expectedDocSpeciality = "Cardiology";
        String expectedDocExperience = "10 years";

        DoctorDto doctor = new DoctorDto(expectedId, expectedDocName, expectedDocSpeciality, expectedDocExperience);

        assertEquals(expectedId, doctor.getId());
        assertEquals(expectedDocName, doctor.getDocName());
        assertEquals(expectedDocSpeciality, doctor.getDocSpeciality());
        assertEquals(expectedDocExperience, doctor.getDocExperience());
    }

    @Test
    void testSetters() {

        DoctorDto doctor = new DoctorDto(1, "Dr. John", "Neurology", "5 years");

        doctor.setId(2);
        doctor.setDocName("Dr. Michael");
        doctor.setDocSpeciality("Orthopedics");
        doctor.setDocExperience("15 years");

        assertEquals(2, doctor.getId());
        assertEquals("Dr. Michael", doctor.getDocName());
        assertEquals("Orthopedics", doctor.getDocSpeciality());
        assertEquals("15 years", doctor.getDocExperience());
    }
}
