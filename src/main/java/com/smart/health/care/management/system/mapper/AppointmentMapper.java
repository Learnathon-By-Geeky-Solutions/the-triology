package com.smart.health.care.management.system.mapper;

import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import com.smart.health.care.management.system.dto.AppointmentDto;
import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDate(),
                appointment.getTime()
        );
    }

    public Appointment toEntity(AppointmentCreateDto dto, Patient patient, Doctor doctor) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        return appointment;
    }
}
