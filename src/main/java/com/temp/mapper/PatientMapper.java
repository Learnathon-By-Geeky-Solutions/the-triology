package com.temp.mapper;

import com.temp.dto.PatientCreateDto;
import com.temp.dto.PatientDto;
import com.temp.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    // Map Entity -> DTO (for response)
    public PatientDto toDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getPhoneNumber()
        );
    }

    // Map DTO -> Entity (for saving/updating)
    public Patient toEntity(PatientCreateDto dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDateOfBirth(dto.getDateOfBirth());
        return patient;
    }
}
