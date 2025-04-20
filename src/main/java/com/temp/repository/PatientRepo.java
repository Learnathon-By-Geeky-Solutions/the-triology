package com.temp.repository;

import com.temp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
