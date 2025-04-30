package com.smart.health.care.management.system.dtotest;

import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

class AppointmentCreateDtoTest {

    @Test
    void testAppointmentCreateDto() {

        AppointmentCreateDto appointmentCreateDto = new AppointmentCreateDto();
        Long expectedPatientId = 123L;
        int expectedDoctorId = 456;
        LocalDate expectedDate = LocalDate.of(2025, 5, 1);
        LocalTime expectedTime = LocalTime.of(10, 30);

        appointmentCreateDto.setPatientId(expectedPatientId);
        appointmentCreateDto.setDoctorId(expectedDoctorId);
        appointmentCreateDto.setDate(expectedDate);
        appointmentCreateDto.setTime(expectedTime);

        assertEquals(expectedPatientId, appointmentCreateDto.getPatientId());
        assertEquals(expectedDoctorId, appointmentCreateDto.getDoctorId());
        assertEquals(expectedDate, appointmentCreateDto.getDate());
        assertEquals(expectedTime, appointmentCreateDto.getTime());
    }
}
