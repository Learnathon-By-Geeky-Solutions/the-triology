package com.smart.health.care.management.system.service;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.PatientMapper;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {


    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepo patientRepo, PatientMapper patientMapper) {
        this.patientRepo = patientRepo;
        this.patientMapper = patientMapper;
    }


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
                .toList();
    }

    private static final String ACTION = "Patient with ID ";
    private static final String ACTION1= " not found";

    public PatientDto getPatientById(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTION + id + ACTION1));
        return patientMapper.toDto(patient);
    }

    public String updatePatient(int id, PatientCreateDto patientCreateDto) {
        validatePatientCreateDto(patientCreateDto);
        Patient existingPatient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTION + id + ACTION1));
        existingPatient.setName(patientCreateDto.getName());
        existingPatient.setPhoneNumber(patientCreateDto.getPhoneNumber());
        existingPatient.setDateOfBirth(patientCreateDto.getDateOfBirth());
        patientRepo.save(existingPatient);
        return "Patient updated";
    }

    public String deletePatient(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTION + id + ACTION1));
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
    public Patient getPatientEntityById(int id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ACTION + id + ACTION1));
    }
}
