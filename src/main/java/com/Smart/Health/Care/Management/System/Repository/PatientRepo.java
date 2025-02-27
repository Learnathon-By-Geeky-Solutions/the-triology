package com.Smart.Health.Care.Management.System.Repository;

import com.Smart.Health.Care.Management.System.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
