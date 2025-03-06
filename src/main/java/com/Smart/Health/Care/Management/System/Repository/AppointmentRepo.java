package com.Smart.Health.Care.Management.System.Repository;

import com.Smart.Health.Care.Management.System.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
