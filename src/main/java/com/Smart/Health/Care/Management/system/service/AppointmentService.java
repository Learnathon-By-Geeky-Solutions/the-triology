package com.smart.health.care.management.system.service;

import com.smart.health.care.management.system.dto.AppointmentCreateDto;
import com.smart.health.care.management.system.dto.AppointmentDto;
import com.smart.health.care.management.system.exception.BusinessLogicException;
import com.smart.health.care.management.system.exception.InvalidInputException;
import com.smart.health.care.management.system.exception.ResourceNotFoundException;
import com.smart.health.care.management.system.mapper.AppointmentMapper;
import com.smart.health.care.management.system.model.Appointment;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.AppointmentRepo;
import com.smart.health.care.management.system.repository.DoctorRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    //@Autowired
    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentMapper appointmentMapper;
    public AppointmentService(AppointmentRepo appointmentRepo, PatientRepo patientRepo,DoctorRepo doctorRepo, AppointmentMapper appointmentMapper){
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentMapper = appointmentMapper;
    }


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
                .toList();
    }

    private static final String APP= "Appointment not found with ID: ";
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(APP + id));
        return appointmentMapper.toDto(appointment);
    }


    public String updateAppointment(Long id, AppointmentCreateDto dto) {
        validateAppointmentCreateDto(dto);

        Appointment existingAppointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(APP+ id));

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
                .orElseThrow(() -> new ResourceNotFoundException(APP + id));
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
