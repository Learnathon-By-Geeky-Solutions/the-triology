package com.smart.health.care.management.system.dtotest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;  // ⭐ you forgot to import this!
import com.smart.health.care.management.system.dto.AppointmentDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentDtoTest {

    private final ObjectMapper objectMapper;

    public AppointmentDtoTest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // ✅ now inside constructor
    }

    @Test
    void testSerialization() throws IOException {
        LocalDate date = LocalDate.of(2025, 4, 30);
        LocalTime time = LocalTime.of(8, 15);
        AppointmentDto appointmentDto = new AppointmentDto(1L, "Shihab", "Dr. Mahtab", date, time);

        String json = objectMapper.writeValueAsString(appointmentDto);

        String expectedJson = "{\"id\":1,\"patientName\":\"Shihab\",\"doctorName\":\"Dr. Mahtab\",\"date\":\"30-04-2025\",\"time\":\"08:15 AM\"}";
        assertEquals(expectedJson, json);
    }

    @Test
    void testDeserialization() throws IOException {
        String json = "{\"id\":1,\"patientName\":\"Shihab\",\"doctorName\":\"Dr. Mahtab\",\"date\":\"30-04-2025\",\"time\":\"08:15 AM\"}";

        AppointmentDto appointmentDto = objectMapper.readValue(json, AppointmentDto.class);

        assertEquals(1L, appointmentDto.getId());
        assertEquals("Shihab", appointmentDto.getPatientName());
        assertEquals("Dr. Mahtab", appointmentDto.getDoctorName());
        assertEquals(LocalDate.of(2025, 4, 30), appointmentDto.getDate());
        assertEquals(LocalTime.of(8, 15), appointmentDto.getTime());
    }

    @Test
    void testDateTimeFormat() {
        LocalDate date = LocalDate.of(2025, 4, 30);
        LocalTime time = LocalTime.of(8, 15);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        String formattedDate = date.format(dateFormatter);
        String formattedTime = time.format(timeFormatter);

        assertEquals("30-04-2025", formattedDate);
        assertEquals("08:15 AM", formattedTime);
    }
}