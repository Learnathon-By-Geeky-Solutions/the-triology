package com.smart.Health.Care.Management.system.service;

import com.smart.Health.Care.Management.system.dto.AppointmentCreateDto;
import com.smart.Health.Care.Management.system.dto.AppointmentDto;
import com.smart.Health.Care.Management.system.exception.BusinessLogicException;
import com.smart.Health.Care.Management.system.exception.InvalidInputException;
import com.smart.Health.Care.Management.system.exception.ResourceNotFoundException;
import com.smart.Health.Care.Management.system.mapper.AppointmentMapper;
import com.smart.Health.Care.Management.system.model.Appointment;
import com.smart.Health.Care.Management.system.model.Doctor;
import com.smart.Health.Care.Management.system.model.Patient;
import com.smart.Health.Care.Management.system.repository.AppointmentRepo;
import com.smart.Health.Care.Management.system.repository.DoctorRepo;
import com.smart.Health.Care.Management.system.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public String addAppointment(AppointmentCreateDto dto) {
        validateAppointmentCreateDto(dto);

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getPatientId()));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctorId()));

        Appointment appointment = appointmentMapper.toEntity(dto, patient, doctor);
        appointmentRepo.save(appointment);
        return "Appointment added";
    }

    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        return appointmentMapper.toDto(appointment);
    }

    public String updateAppointment(Long id, AppointmentCreateDto dto) {
        validateAppointmentCreateDto(dto);

        Appointment existingAppointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getPatientId()));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctorId()));

        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);
        existingAppointment.setDate(dto.getDate());
        existingAppointment.setTime(dto.getTime());

        appointmentRepo.save(existingAppointment);
        return "Appointment updated";
    }

    public String deleteAppointment(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        appointmentRepo.delete(appointment);
        return "Appointment deleted";
    }

    private void validateAppointmentCreateDto(AppointmentCreateDto dto) {
        if (dto.getDate() == null || dto.getDate().isBefore(LocalDate.now())) {
            throw new BusinessLogicException("Appointment date must be today or in the future.");
        }
        if (dto.getTime() == null) {
            throw new InvalidInputException("Appointment time must not be null.");
        }
    }
}
