package com.smart.health.care.management.system.repository;

import com.smart.health.care.management.system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);
}
