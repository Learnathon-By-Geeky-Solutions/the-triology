package com.smart.health.care.management.system.repository;

import com.smart.health.care.management.system.model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepo extends JpaRepository<HealthProfile, Integer> {

    HealthProfile findByPatient_Id(Long patientId);
}
