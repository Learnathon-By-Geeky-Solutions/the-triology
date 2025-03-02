package com.Smart.Health.Care.Management.System.Controller;


import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
    @GetMapping("")
    public ResponseEntity<List<Doctor>> getDoctors() {return ResponseEntity.ok(doctorService.getAllDoctors());}

    @GetMapping("/id")
    public ResponseEntity<Doctor> getDoctorById(@RequestParam("id") int id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
    @PutMapping
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctor));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDoctorById(@RequestParam("id") int id) {
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }


}
