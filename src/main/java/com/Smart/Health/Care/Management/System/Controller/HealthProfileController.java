package com.Smart.Health.Care.Management.System.Controller;

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
    public ResponseEntity<HealthProfile> getHealthProfile(@PathVariable Long patientId) {
        HealthProfile healthProfile = healthProfileService.getHealthProfile(patientId);
        if (healthProfile != null) {
            return ResponseEntity.ok(healthProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get All HealthProfiles
    @GetMapping("/")
    public ResponseEntity<List<HealthProfile>> getAllHealthProfiles() {
        List<HealthProfile> healthProfiles = healthProfileService.getAllHealthProfile();
        if (!healthProfiles.isEmpty()) {
            return ResponseEntity.ok(healthProfiles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping("/save")
    public ResponseEntity<HealthProfile> saveHealthProfile(@RequestBody HealthProfile healthProfile) {
        HealthProfile savedProfile = healthProfileService.saveHealthProfile(healthProfile);
        return ResponseEntity.ok(savedProfile);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHealthProfile(@PathVariable Long id) {
        healthProfileService.deleteHealthProfile(id);
        return ResponseEntity.noContent().build();
    }
}
