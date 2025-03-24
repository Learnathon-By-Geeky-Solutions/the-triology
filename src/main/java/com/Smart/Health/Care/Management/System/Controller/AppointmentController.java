package com.Smart.Health.Care.Management.System.Controller;

import com.Smart.Health.Care.Management.System.DTO.AppointmentCreateDto;
import com.Smart.Health.Care.Management.System.DTO.AppointmentDto;
import com.Smart.Health.Care.Management.System.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ Add Appointment
    @PostMapping("")
    public ResponseEntity<String> addAppointment(@RequestBody AppointmentCreateDto dto) {
        return ResponseEntity.ok(appointmentService.addAppointment(dto));
    }

    // ✅ Get All Appointments
    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ✅ Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    // ✅ Update Appointment
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id, @RequestBody AppointmentCreateDto dto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, dto));
    }

    // ✅ Delete Appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
