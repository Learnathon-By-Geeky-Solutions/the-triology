package com.temp.mapper;

import com.temp.dto.AppointmentCreateDto;
import com.temp.dto.AppointmentDto;
import com.temp.model.Appointment;
import com.temp.model.Doctor;
import com.temp.model.Patient;
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
