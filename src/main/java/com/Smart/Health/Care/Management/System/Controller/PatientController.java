package com.Smart.Health.Care.Management.System.Controller;

import com.Smart.Health.Care.Management.System.DTO.PatientCreateDto;
import com.Smart.Health.Care.Management.System.DTO.PatientDto;
import com.Smart.Health.Care.Management.System.Response.CustomResponse;
import com.Smart.Health.Care.Management.System.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<CustomResponse<String>> addPatient(@RequestBody PatientCreateDto dto) {
        String result = patientService.addPatient(dto);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Patient added successfully.", result));
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<PatientDto>>> getPatients() {
        List<PatientDto> list = patientService.getAllPatients();
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Fetched all patients.", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<PatientDto>> getPatient(@PathVariable int id) {
        PatientDto dto = patientService.getPatientById(id);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Fetched patient by ID.", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> updatePatient(@PathVariable int id, @RequestBody PatientCreateDto dto) {
        String result = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Patient updated successfully.", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deletePatient(@PathVariable int id) {
        String result = patientService.deletePatient(id);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Patient deleted successfully.", result));
    }
}
