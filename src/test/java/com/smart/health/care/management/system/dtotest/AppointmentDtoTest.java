package com.smart.health.care.management.system.dtotest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.health.care.management.system.dto.AppointmentDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentDtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerialization() throws IOException {
        // Creating an AppointmentDto object
        LocalDate date = LocalDate.of(2025, 4, 30); // Date: 30-04-2025
        LocalTime time = LocalTime.of(8, 15); // Time: 08:15 AM
        AppointmentDto appointmentDto = new AppointmentDto(1L, "Shihab", "Dr. Mahtab", date, time);

        // Serializing AppointmentDto to JSON
        String json = objectMapper.writeValueAsString(appointmentDto);

        // Expected JSON format
        String expectedJson = "{\"id\":1,\"patientName\":\"Shihab\",\"doctorName\":\"Dr. Mahtab\",\"date\":\"30-04-2025\",\"time\":\"08:15 AM\"}";

        // Assert that the serialized JSON matches the expected format
        assertEquals(expectedJson, json);
    }

    @Test
    void testDeserialization() throws IOException {
        // JSON string to be deserialized
        String json = "{\"id\":1,\"patientName\":\"Shihab\",\"doctorName\":\"Dr. Mahtab\",\"date\":\"30-04-2025\",\"time\":\"08:15 AM\"}";

        // Deserializing the JSON string to AppointmentDto object
        AppointmentDto appointmentDto = objectMapper.readValue(json, AppointmentDto.class);

        // Assert that the fields match the expected values
        assertEquals(1L, appointmentDto.getId());
        assertEquals("Shihab", appointmentDto.getPatientName());
        assertEquals("Dr. Mahtab", appointmentDto.getDoctorName());
        assertEquals(LocalDate.of(2025, 4, 30), appointmentDto.getDate());
        assertEquals(LocalTime.of(8, 15), appointmentDto.getTime());
    }

    @Test
    void testDateTimeFormat() {
        // Test date format with LocalDate and LocalTime
        LocalDate date = LocalDate.of(2025, 4, 30); // Date: 30-04-2025
        LocalTime time = LocalTime.of(8, 15); // Time: 08:15 AM

        // Format date and time manually to check the formatting
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        String formattedDate = date.format(dateFormatter); // "30-04-2025"
        String formattedTime = time.format(timeFormatter); // "08:15 AM"

        // Assert the formatted date and time
        assertEquals("30-04-2025", formattedDate);
        assertEquals("08:15 AM", formattedTime);
    }
}
