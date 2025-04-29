package com.smart.health.care.management.system.repository;

import com.smart.health.care.management.system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByEmail(String email);
    @Query(value = """
    SELECT * FROM doctor 
    WHERE CAST(split_part(experience, ' ', 1) AS INTEGER) >= :minExp
    ORDER BY CAST(split_part(experience, ' ', 1) AS INTEGER) DESC
    """, nativeQuery = true)
    List<Doctor> findDoctorsWithMinExperience(@Param("minExp") int minExp);

}
