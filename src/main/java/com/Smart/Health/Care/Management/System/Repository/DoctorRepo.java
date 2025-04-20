package com.Smart.Health.Care.Management.System.Repository;

import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);
}
