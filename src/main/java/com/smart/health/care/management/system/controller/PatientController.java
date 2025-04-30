package com.smart.health.care.management.system.controller;

import com.smart.health.care.management.system.dto.PatientCreateDto;
import com.smart.health.care.management.system.dto.PatientDto;
import com.smart.health.care.management.system.model.Patient;
import org.springframework.security.core.Authentication;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    private static final String RCOD = "S0000";
    @PostMapping
    public ResponseEntity<CustomResponse<String>> addPatient(@RequestBody PatientCreateDto dto) {
        String result = patientService.addPatient(dto);
        return ResponseEntity.ok(new CustomResponse<>(RCOD, "Patient added successfully.", result));
    }


    @GetMapping("/me")
    public ResponseEntity<Patient> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Patient currentUser = (Patient) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<PatientDto>>> getPatients() {
        List<PatientDto> list = patientService.getAllPatients();
        return ResponseEntity.ok(new CustomResponse<>(RCOD, "Fetched all patients.", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<PatientDto>> getPatient(@PathVariable Long id) {
        PatientDto dto = patientService.getPatientById(id);
        return ResponseEntity.ok(new CustomResponse<>(RCOD, "Fetched patient by ID.", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> updatePatient(@PathVariable Long id, @RequestBody PatientCreateDto dto) {
        String result = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(new CustomResponse<>(RCOD, "Patient updated successfully.", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deletePatient(@PathVariable Long id) {
        String result = patientService.deletePatient(id);
        return ResponseEntity.ok(new CustomResponse<>(RCOD, "Patient deleted successfully.", result));
    }
}
