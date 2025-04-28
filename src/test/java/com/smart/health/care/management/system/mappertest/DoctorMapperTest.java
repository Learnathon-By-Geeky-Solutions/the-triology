package com.smart.health.care.management.system.mappertest;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.DoctorDto;
import com.smart.health.care.management.system.mapper.DoctorMapper;
import com.smart.health.care.management.system.model.Doctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorMapperTest {

    private final DoctorMapper doctorMapper = new DoctorMapper();

    @Test
    void testToDto() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("Dr. John Doe");
        doctor.setSpecialty("Cardiology");
        doctor.setExperience("10");

        // Act
        DoctorDto dto = doctorMapper.toDto(doctor);

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("Dr. John Doe", dto.getDocName());
        assertEquals("Cardiology", dto.getDocSpeciality());
        assertEquals("10", dto.getDocExperience());
    }

    @Test
    void testToEntity() {
        // Arrange
        DoctorCreateDto createDto = new DoctorCreateDto();
        createDto.setDoctorName("Dr. Jane Smith");
        createDto.setDoctorSpeciality("Neurology");
        createDto.setDoctorExperience("10");
        createDto.setDoctorEmail("jane.smith@example.com");
        createDto.setDoctorPhone("+9876543210");
        createDto.setDoctorPassword("securePassword123");

        // Act
        Doctor doctor = doctorMapper.toEntity(createDto);

        // Assert
        assertEquals("Dr. Jane Smith", doctor.getName());
        assertEquals("Neurology", doctor.getSpecialty());
        assertEquals("10", doctor.getExperience());
        assertEquals("jane.smith@example.com", doctor.getEmail());
        assertEquals("+9876543210", doctor.getPhone());
        assertEquals("securePassword123", doctor.getPassword());
    }
}
