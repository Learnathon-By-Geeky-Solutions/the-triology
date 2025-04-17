package com.smart.Health.Care.Management.system.mapper;

import com.smart.Health.Care.Management.system.dto.HealthProfileDto;
import com.smart.Health.Care.Management.system.dto.HealthProfileCreateDto;
import com.smart.Health.Care.Management.system.model.HealthProfile;
import com.smart.Health.Care.Management.system.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class HealthProfileMapper {

    public HealthProfileDto toDto(HealthProfile healthProfile) {
        HealthProfileDto dto = new HealthProfileDto();
        dto.setId(healthProfile.getId());
        dto.setPatientId((long) healthProfile.getPatient().getId());
        dto.setHeight(healthProfile.getHeight());
        dto.setWeight(healthProfile.getWeight());
        dto.setBloodGroup(healthProfile.getBloodGroup());
        dto.setAllergies(healthProfile.getAllergies());
        dto.setMedicalHistory(healthProfile.getMedicalHistory());
        dto.setConditions(healthProfile.getConditions());
        return dto;
    }

    public HealthProfile toEntity(HealthProfileCreateDto dto, Patient patient) {
        HealthProfile entity = new HealthProfile();
        entity.setPatient(patient);
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        entity.setBloodGroup(dto.getBloodGroup());
        entity.setAllergies(dto.getAllergies());
        entity.setMedicalHistory(dto.getMedicalHistory());
        entity.setConditions(dto.getConditions());
        return entity;
    }
}
