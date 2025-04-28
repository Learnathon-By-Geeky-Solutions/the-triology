package com.smart.health.care.management.system.repository;

import com.smart.health.care.management.system.model.HealthProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepo extends JpaRepository<HealthProfile, Long> {

    HealthProfile findByPatient_Id(Long patientId);

    HealthProfile findByPatient_Id(Long patientId, Sort sort);

    HealthProfile findByPatient_Id(Long patientId, Pageable pageable);

    HealthProfile findByPatient_Id(Long patientId);
}
