package com.smart.health.care.management.system.controller;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.DoctorDto;

import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.smart.health.care.management.system.response.CustomResponse;
import com.smart.health.care.management.system.service.DoctorService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    private static final String RCODE= "S0000";
    @PostMapping("")
    public ResponseEntity<CustomResponse<String>> addDoctor(@RequestBody DoctorCreateDto dto) {

        String result= doctorService.addDoctor(dto);
        return ResponseEntity.ok(new CustomResponse<>(RCODE, "Doctor added successfully.", result));
    }
    @GetMapping("")
    public ResponseEntity<CustomResponse<List<DoctorDto>>> getDoctors() {
        List<DoctorDto> list = doctorService.getAllDoctors();
        return ResponseEntity.ok(new CustomResponse<>(RCODE, "Fetched all doctors.", list));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<DoctorDto>> getDoctorById(@PathVariable int id) {
        DoctorDto dto = doctorService.getDoctorById(id);
        return ResponseEntity.ok(new CustomResponse<>(RCODE, "Fetched Doctor by ID.", dto));
    }

    @GetMapping("/me")
    public ResponseEntity<Doctor> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Doctor currentUser = (Doctor) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> updateDoctor(@PathVariable int id, @RequestBody DoctorCreateDto dto) {
        String result = doctorService.updateDoctor(id,dto);
        return ResponseEntity.ok(new CustomResponse<>(RCODE, "Doctor updated successfully.", result));
    }

    @DeleteMapping
    public ResponseEntity<CustomResponse<String>> deleteDoctorById(@RequestParam("id") int id) {
        String result = doctorService.deleteDoctor(id);
        return ResponseEntity.ok(new CustomResponse<>(RCODE, "Doctor deleted successfully.", result));
    }


}
