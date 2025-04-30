package com.smart.health.care.management.system.mapper;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        String fullName = patient.getName();

        return new PatientDto(
                patient.getId(),
                fullName,
                patient.getPhoneNumber()
        );
    }

    public Patient toEntity(PatientCreateDto dto) {
        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDateOfBirth(parseDateOfBirth(dto.getDateOfBirth()));

        return patient;
    }

    private LocalDate parseDateOfBirth(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
