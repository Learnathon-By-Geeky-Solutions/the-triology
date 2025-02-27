package com.Smart.Health.Care.Management.System.Controller;
import com.Smart.Health.Care.Management.System.Model.Patient;
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

    @PostMapping("")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }
    @GetMapping("")
    public ResponseEntity<List<Patient>> getPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
    @PutMapping
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(patient));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }
}
