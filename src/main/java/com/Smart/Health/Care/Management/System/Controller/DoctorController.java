package com.Smart.Health.Care.Management.System.Controller;


import com.Smart.Health.Care.Management.System.DTO.DoctorCreateDto;
import com.Smart.Health.Care.Management.System.DTO.DoctorDto;
import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Response.CustomResponse;
import com.Smart.Health.Care.Management.System.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("")
    public ResponseEntity<CustomResponse<String>> addDoctor(@RequestBody DoctorCreateDto dto) {
        System.out.println("Received doctor name: " + dto.getDoctorName());
        String result= doctorService.addDoctor(dto);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Doctor added successfully.", result));
    }
    @GetMapping("")
    public ResponseEntity<CustomResponse<List<DoctorDto>>> getDoctors() {
        List<DoctorDto> list = doctorService.getAllDoctors();
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Fetched all doctors.", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<DoctorDto>> getDoctorById(@PathVariable int id) {
        DoctorDto dto = doctorService.getDoctorById(id);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Fetched Doctor by ID.", dto));
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
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Doctor updated successfully.", result));
    }

    @DeleteMapping
    public ResponseEntity<CustomResponse<String>> deleteDoctorById(@RequestParam("id") int id) {
        String result = doctorService.deleteDoctor(id);
        return ResponseEntity.ok(new CustomResponse<>("S0000", "Doctor deleted successfully.", result));
    }


}
