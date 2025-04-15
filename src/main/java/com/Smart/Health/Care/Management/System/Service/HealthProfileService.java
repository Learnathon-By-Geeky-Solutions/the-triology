package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.DTO.HealthProfileCreateDto;
import com.Smart.Health.Care.Management.System.DTO.HealthProfileDto;
import com.Smart.Health.Care.Management.System.Exception.InvalidInputException;
import com.Smart.Health.Care.Management.System.Exception.ResourceNotFoundException;
import com.Smart.Health.Care.Management.System.Mapper.HealthProfileMapper;
import com.Smart.Health.Care.Management.System.Model.HealthProfile;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.HealthProfileRepo;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthProfileService {

    @Autowired
    private HealthProfileRepo healthProfileRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private HealthProfileMapper healthProfileMapper;

    @Autowired
    private PatientService patientService;

    public List<HealthProfileDto> getAllHealthProfiles() {
        return healthProfileRepo.findAll()
                .stream()
                .map(healthProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    // Method to get a HealthProfile by Patient ID
    public HealthProfileDto getHealthProfileByPatientId(Long patientId) {
        HealthProfile profile = healthProfileRepo.findByPatientId(patientId.intValue());
        if (profile == null) {
            throw new ResourceNotFoundException("Health profile not found for patient ID: " + patientId);
        }
        return healthProfileMapper.toDto(profile);
    }

    // Method to save or update a HealthProfile
    public HealthProfileDto createHealthProfile(HealthProfileCreateDto createDto) {
        validateHealthProfileDto(createDto);
        Patient patient = patientService.getPatientEntityById(Math.toIntExact(createDto.getPatientId()));
        HealthProfile profile = healthProfileMapper.toEntity(createDto, patient);
        HealthProfile saved = healthProfileRepo.save(profile);
        return healthProfileMapper.toDto(saved);
    }

    public String updateHealthProfile(Long patientId, HealthProfileCreateDto updateDto) {
        HealthProfile existingProfile = healthProfileRepo.findByPatientId(Math.toIntExact(patientId));
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


    // Method to delete a HealthProfile by ID
    public String deleteHealthProfile(Long id) {
        HealthProfile hp = healthProfileRepo.findByPatientId(Math.toIntExact(id));
        if (hp == null) {
            throw new ResourceNotFoundException("Health Profile with ID " + id + " not found");
        }
        healthProfileRepo.delete(hp);
        return "Successfully deleted Health Profile with ID " + id;
    }

    private void validateHealthProfileDto(HealthProfileCreateDto dto){
        if (dto.getPatientId() == null || dto.getPatientId() <= 0) {
            throw new InvalidInputException("Patient ID is required");
        }
        if (dto.getBloodGroup() == null || dto.getBloodGroup().trim().isEmpty()) {
            throw new InvalidInputException("Blood group must not be empty.");
        }
        if(dto.getConditions() == null || dto.getConditions().trim().isEmpty()) {
            throw new InvalidInputException("Conditions must not be empty.");
        }
        if (dto.getHeight()<= 0 ) {
            throw new InvalidInputException("Height must be a positive number.");
        }
        if(dto.getWeight()<=0) {
            throw new InvalidInputException("Weight must be a positive number.");
        }

    }
}

