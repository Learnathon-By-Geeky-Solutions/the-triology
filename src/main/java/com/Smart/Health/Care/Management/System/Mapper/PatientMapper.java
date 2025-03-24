package com.Smart.Health.Care.Management.System.Mapper;

import com.Smart.Health.Care.Management.System.DTO.PatientCreateDto;
import com.Smart.Health.Care.Management.System.DTO.PatientDto;
import com.Smart.Health.Care.Management.System.Model.Patient;
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
