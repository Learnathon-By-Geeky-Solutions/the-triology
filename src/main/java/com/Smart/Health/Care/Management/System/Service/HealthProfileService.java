package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.Model.HealthProfile;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.HealthProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthProfileService {

    @Autowired
    private HealthProfileRepository healthProfileRepository;

    public List<HealthProfile> getAllHealthProfile() {
        return healthProfileRepository.findAll();
    }


    // Method to get a HealthProfile by Patient ID
    public HealthProfile getHealthProfile(Long patientId) {
        return healthProfileRepository.findByPatientId(patientId);
    }

    // Method to save or update a HealthProfile
    public HealthProfile saveHealthProfile(HealthProfile healthProfile) {
        return healthProfileRepository.save(healthProfile);
    }

    // Method to delete a HealthProfile by ID
    public void deleteHealthProfile(Long id) {
        healthProfileRepository.deleteById(id);
    }
}
