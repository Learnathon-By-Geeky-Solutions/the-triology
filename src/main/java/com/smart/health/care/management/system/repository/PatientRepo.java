package com.smart.health.care.management.system.repository;

import com.smart.health.care.management.system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
