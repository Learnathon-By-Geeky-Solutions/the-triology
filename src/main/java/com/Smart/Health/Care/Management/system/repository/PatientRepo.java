package com.smart.Health.Care.Management.system.repository;

import com.smart.Health.Care.Management.system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
