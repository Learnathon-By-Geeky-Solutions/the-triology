package com.temp.controller;

import com.temp.dto.AppointmentCreateDto;
import com.temp.dto.AppointmentDto;
import com.temp.response.CustomResponse;
import com.temp.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    private static final String CODE = "S0000";
    @PostMapping("")
    public ResponseEntity<CustomResponse<String>> addAppointment(@RequestBody AppointmentCreateDto dto) {
        String result = appointmentService.addAppointment(dto);
        return ResponseEntity.ok(new CustomResponse<>(CODE, "Appointment added successfully.", result));
    }

    @GetMapping("")
    public ResponseEntity<CustomResponse<List<AppointmentDto>>> getAppointments() {
        List<AppointmentDto> list = appointmentService.getAllAppointments();
        return ResponseEntity.ok(new CustomResponse<>(CODE, "Fetched all appointments.", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<AppointmentDto>> getAppointmentById(@PathVariable Long id) {
        AppointmentDto dto = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(new CustomResponse<>(CODE, "Fetched appointment by ID.", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> updateAppointment(@PathVariable Long id, @RequestBody AppointmentCreateDto dto) {
        String result = appointmentService.updateAppointment(id, dto);
        return ResponseEntity.ok(new CustomResponse<>(CODE, "Appointment updated successfully.", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteAppointment(@PathVariable Long id) {
        String result = appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(new CustomResponse<>(CODE, "Appointment deleted successfully.", result));
    }
}
