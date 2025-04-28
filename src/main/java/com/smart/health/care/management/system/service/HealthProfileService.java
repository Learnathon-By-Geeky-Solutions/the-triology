package com.smart.health.care.management.system.service;

import com.smart.health.care.management.system.dto.HealthProfileCreateDto;
import com.smart.health.care.management.system.dto.HealthProfileDto;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.HealthProfileMapper;
import com.smart.health.care.management.system.model.HealthProfile;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.HealthProfileRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthProfileService {

    private final HealthProfileRepo healthProfileRepo;
    private final HealthProfileMapper healthProfileMapper;
    private final PatientService patientService;

    public HealthProfileService(HealthProfileRepo healthProfileRepo, HealthProfileMapper healthProfileMapper, PatientService patientService) {
        this.healthProfileRepo = healthProfileRepo;
        this.healthProfileMapper = healthProfileMapper;
        this.patientService = patientService;
    }

    // Get all health profiles
    public List<HealthProfileDto> getAllHealthProfiles() {
        return healthProfileRepo.findAll()
                .stream()
                .map(healthProfileMapper::toDto)
                .toList();
    }

    // Get health profile by patient ID
    public HealthProfileDto getHealthProfileByPatientId(Long patientId) {
        HealthProfile profile = healthProfileRepo.findByPatient_Id(patientId);  // Updated to use Long
        if (profile == null) {
            throw new ResourceNotFoundException("Health profile not found for patient ID: " + patientId);
        }
        return healthProfileMapper.toDto(profile);
    }

    // Create or update health profile
    public HealthProfileDto createHealthProfile(HealthProfileCreateDto createDto) {
        validateHealthProfileDto(createDto);
        Patient patient = patientService.getPatientEntityById(createDto.getPatientId());  // Updated to use Long
        HealthProfile profile = healthProfileMapper.toEntity(createDto, patient);
        HealthProfile saved = healthProfileRepo.save(profile);
        return healthProfileMapper.toDto(saved);
    }

    // Update health profile
    public String updateHealthProfile(Long patientId, HealthProfileCreateDto updateDto) {
        HealthProfile existingProfile = healthProfileRepo.findByPatient_Id(patientId);  // Updated to use Long
        if (existingProfile == null) {
            throw new ResourceNotFoundException("HealthProfile for Patient ID " + patientId + " not found");
        }
        validateHealthProfileDto(updateDto);
        existingProfile.setBloodGroup(updateDto.getBloodGroup());
        existingProfile.setAllergies(updateDto.getAllergies());
        existingProfile.setHeight(updateDto.getHeight());
        existingProfile.setWeight(updateDto.getWeight());
        existingProfile.setConditions(updateDto.getConditions());
        existingProfile.setMedicalHistory(updateDto.getMedicalHistory());
        healthProfileRepo.save(existingProfile);
        return "Health Profile updated successfully";
    }

    // Delete health profile by ID
    public String deleteHealthProfile(Long id) {
        HealthProfile hp = healthProfileRepo.findByPatient_Id(id);  // Updated to use Long
        if (hp == null) {
            throw new ResourceNotFoundException("Health Profile with ID " + id + " not found");
        }
        healthProfileRepo.delete(hp);
        return "Successfully deleted Health Profile with ID " + id;
    }

    // Validate health profile input
    private void validateHealthProfileDto(HealthProfileCreateDto dto) {
        if (dto.getPatientId() == null || dto.getPatientId() <= 0) {
            throw new InvalidInputException("Patient ID is required");
        }
        if (dto.getBloodGroup() == null || dto.getBloodGroup().trim().isEmpty()) {
            throw new InvalidInputException("Blood group must not be empty.");
        }
        if (dto.getConditions() == null || dto.getConditions().trim().isEmpty()) {
            throw new InvalidInputException("Conditions must not be empty.");
        }
        if (dto.getHeight() <= 0) {
            throw new InvalidInputException("Height must be a positive number.");
        }
        if (dto.getWeight() <= 0) {
            throw new InvalidInputException("Weight must be a positive number.");
        }
    }
}
