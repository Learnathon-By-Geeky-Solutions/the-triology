package com.Smart.Health.Care.Management.System.Controller;

import com.Smart.Health.Care.Management.System.DTO.HealthProfileCreateDto;
import com.Smart.Health.Care.Management.System.DTO.HealthProfileDto;
import com.Smart.Health.Care.Management.System.Model.HealthProfile;
import com.Smart.Health.Care.Management.System.Service.HealthProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthprofiles")
public class HealthProfileController {

    @Autowired
    private HealthProfileService healthProfileService;

    // Get HealthProfile by Patient ID
    @GetMapping("/{patientId}")
    public ResponseEntity<HealthProfileDto> getHealthProfile(@PathVariable Long patientId) {
        HealthProfileDto healthProfile = healthProfileService.getHealthProfileByPatientId(patientId);
        return ResponseEntity.ok(healthProfile);
    }

    // Get All HealthProfiles
    @GetMapping("/")
    public ResponseEntity<List<HealthProfileDto>> getAllHealthProfiles() {
        List<HealthProfileDto> healthProfiles = healthProfileService.getAllHealthProfiles();
        if (!healthProfiles.isEmpty()) {
            return ResponseEntity.ok(healthProfiles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping("/save")
    public ResponseEntity<HealthProfileDto> saveHealthProfile(@RequestBody HealthProfileCreateDto healthProfileCreateDto) {
        HealthProfileDto savedProfile = healthProfileService.createHealthProfile(healthProfileCreateDto);
        return ResponseEntity.ok(savedProfile);
    }


    @PutMapping("/update/{patientId}")
    public ResponseEntity<String> updateHealthProfile(@PathVariable Long patientId, @RequestBody HealthProfileCreateDto healthProfileCreateDto) {
        String message = healthProfileService.updateHealthProfile(patientId, healthProfileCreateDto);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHealthProfile(@PathVariable Long id) {
        healthProfileService.deleteHealthProfile(id);
        return ResponseEntity.noContent().build();
    }
}