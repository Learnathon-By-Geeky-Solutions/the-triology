package com.smart.Health.Care.Management.system.service;

import com.smart.Health.Care.Management.system.dto.PatientCreateDto;
import com.smart.Health.Care.Management.system.dto.PatientDto;
import com.smart.Health.Care.Management.system.exception.BusinessLogicException;
import com.smart.Health.Care.Management.system.exception.InvalidInputException;
import com.smart.Health.Care.Management.system.exception.ResourceNotFoundException;
import com.smart.Health.Care.Management.system.mapper.PatientMapper;
import com.smart.Health.Care.Management.system.model.Patient;
import com.smart.Health.Care.Management.system.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientMapper patientMapper;

    public String addPatient(PatientCreateDto patientCreateDto) {
        validatePatientCreateDto(patientCreateDto);
        Patient patient = patientMapper.toEntity(patientCreateDto);
        patientRepo.save(patient);
        return "Patient added";
    }

    public List<PatientDto> getAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    private static final String action = "Patient with ID ";
    private static final String action1= " not found";

    public PatientDto getPatientById(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(action + id + action1));
        return patientMapper.toDto(patient);
    }

    public String updatePatient(int id, PatientCreateDto patientCreateDto) {
        validatePatientCreateDto(patientCreateDto);
        Patient existingPatient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(action + id + action1));
        existingPatient.setName(patientCreateDto.getName());
        existingPatient.setPhoneNumber(patientCreateDto.getPhoneNumber());
        existingPatient.setDateOfBirth(patientCreateDto.getDateOfBirth());
        patientRepo.save(existingPatient);
        return "Patient updated";
    }

    public String deletePatient(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(action + id + action));
        patientRepo.delete(patient);
        return "Patient deleted";
    }

    private void validatePatientCreateDto(PatientCreateDto dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new InvalidInputException("Patient name cannot be empty.");
        }
        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().trim().length() < 11) {
            throw new InvalidInputException("Phone number must be at least 11 digits.");
        }
        if (dto.getDateOfBirth() == null || dto.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new BusinessLogicException("Date of birth cannot be in the future.");
        }
    }
    public com.smart.Health.Care.Management.system.model.Patient getPatientEntityById(int id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(action + id + action1));
    }
}
