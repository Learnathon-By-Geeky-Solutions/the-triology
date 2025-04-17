package com.smart.Health.Care.Management.system.repository;

import com.smart.Health.Care.Management.system.model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepo extends JpaRepository<HealthProfile, Integer> {

    HealthProfile findByPatientId(Integer patientId);
}
