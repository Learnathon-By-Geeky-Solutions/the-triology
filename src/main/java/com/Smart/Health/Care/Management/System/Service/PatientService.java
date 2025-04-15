package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.DTO.PatientCreateDto;
import com.Smart.Health.Care.Management.System.DTO.PatientDto;
import com.Smart.Health.Care.Management.System.Exception.BusinessLogicException;
import com.Smart.Health.Care.Management.System.Exception.InvalidInputException;
import com.Smart.Health.Care.Management.System.Exception.ResourceNotFoundException;
import com.Smart.Health.Care.Management.System.Mapper.PatientMapper;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
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

    public PatientDto getPatientById(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
        return patientMapper.toDto(patient);
    }

    public String updatePatient(int id, PatientCreateDto patientCreateDto) {
        validatePatientCreateDto(patientCreateDto);
        Patient existingPatient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
        existingPatient.setName(patientCreateDto.getName());
        existingPatient.setPhoneNumber(patientCreateDto.getPhoneNumber());
        existingPatient.setDateOfBirth(patientCreateDto.getDateOfBirth());
        patientRepo.save(existingPatient);
        return "Patient updated";
    }

    public String deletePatient(int id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
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
                .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
    }
}
