package com.Smart.Health.Care.Management.System.Repository;

import com.Smart.Health.Care.Management.System.Model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProfileRepo extends JpaRepository<HealthProfile, Integer> {

    HealthProfile findByPatientId(Integer patientId);
}
