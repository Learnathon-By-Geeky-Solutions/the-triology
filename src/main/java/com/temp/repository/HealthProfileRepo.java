package com.temp.repository;

import com.temp.model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepo extends JpaRepository<HealthProfile, Integer> {

    HealthProfile findByPatientId(Integer patientId);
}
