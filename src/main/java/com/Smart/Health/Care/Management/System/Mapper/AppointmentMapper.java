package com.Smart.Health.Care.Management.System.Mapper;

import com.Smart.Health.Care.Management.System.DTO.AppointmentCreateDto;
import com.Smart.Health.Care.Management.System.DTO.AppointmentDto;
import com.Smart.Health.Care.Management.System.Model.Appointment;
import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Model.Patient;
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
