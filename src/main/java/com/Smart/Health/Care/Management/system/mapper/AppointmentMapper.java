package com.smart.Health.Care.Management.system.mapper;

import com.smart.Health.Care.Management.system.dto.AppointmentCreateDto;
import com.smart.Health.Care.Management.system.dto.AppointmentDto;
import com.smart.Health.Care.Management.system.model.Appointment;
import com.smart.Health.Care.Management.system.model.Doctor;
import com.smart.Health.Care.Management.system.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    // Convert Entity → DTO (return patient and doctor names instead of IDs)
    public AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDate(),
                appointment.getTime()
        );
    }

    // Convert DTO → Entity
    public Appointment toEntity(AppointmentCreateDto dto, Patient patient, Doctor doctor) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        return appointment;
    }
}
