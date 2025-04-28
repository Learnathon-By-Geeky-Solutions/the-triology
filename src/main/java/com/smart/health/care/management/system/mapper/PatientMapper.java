package com.smart.health.care.management.system.mapper;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PatientMapper {

    // Map Entity -> DTO (for response)
    public PatientDto toDto(Patient patient) {
        // If patient has separate first and last name fields, concatenate them for the full name
        String fullName = patient.getName(); // Assuming Patient has firstName and lastName fields

        return new PatientDto(
                patient.getId(),
                fullName,
                patient.getPhoneNumber()  // Assuming phoneNumber is already in the Patient model
        );
    }

    // Map DTO -> Entity (for saving/updating)
    public Patient toEntity(PatientCreateDto dto) {
        Patient patient = new Patient();

        // Directly set the full name
        patient.setName(dto.getName());

        patient.setPhoneNumber(dto.getPhoneNumber());  // Assuming phoneNumber in PatientCreateDto
        patient.setDateOfBirth(parseDateOfBirth(dto.getDateOfBirth()));  // Parse and set the dateOfBirth

        return patient;
    }

    // Method to parse the string date to LocalDate
    private LocalDate parseDateOfBirth(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateOfBirth, formatter);  // Parse the string to LocalDate
    }
}
