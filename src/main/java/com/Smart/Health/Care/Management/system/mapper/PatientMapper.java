package com.smart.Health.Care.Management.system.mapper;

import com.smart.Health.Care.Management.system.dto.PatientCreateDto;
import com.smart.Health.Care.Management.system.dto.PatientDto;
import com.smart.Health.Care.Management.system.model.Patient;
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
