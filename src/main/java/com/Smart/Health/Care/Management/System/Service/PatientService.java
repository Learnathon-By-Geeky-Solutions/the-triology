package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.DTO.PatientCreateDto;
import com.Smart.Health.Care.Management.System.DTO.PatientDto;
import com.Smart.Health.Care.Management.System.Mapper.PatientMapper;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientMapper patientMapper;

    public String addPatient(PatientCreateDto patientCreateDto) {
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
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toDto(patient);
    }

    public String updatePatient(int id, PatientCreateDto patientCreateDto) {
        Patient existingPatient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        existingPatient.setName(patientCreateDto.getName());
        existingPatient.setPhoneNumber(patientCreateDto.getPhoneNumber());
        existingPatient.setDateOfBirth(patientCreateDto.getDateOfBirth());
        patientRepo.save(existingPatient);
        return "Patient updated";
    }

    public String deletePatient(int id) {
        patientRepo.deleteById(id);
        return "Patient deleted";
    }
}
