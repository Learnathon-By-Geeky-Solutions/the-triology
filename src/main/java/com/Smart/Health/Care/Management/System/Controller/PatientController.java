package com.Smart.Health.Care.Management.System.Controller;

import com.Smart.Health.Care.Management.System.DTO.PatientCreateDto;
import com.Smart.Health.Care.Management.System.DTO.PatientDto;
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
    public ResponseEntity<String> addPatient(@RequestBody PatientCreateDto dto) {
        return ResponseEntity.ok(patientService.addPatient(dto));
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable int id, @RequestBody PatientCreateDto dto) {
        return ResponseEntity.ok(patientService.updatePatient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }
}
