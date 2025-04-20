package com.temp.service;

import com.temp.dto.AppointmentCreateDto;
import com.temp.dto.AppointmentDto;
import com.temp.exception.BusinessLogicException;
import com.temp.exception.InvalidInputException;
import com.temp.exception.ResourceNotFoundException;
import com.temp.mapper.AppointmentMapper;
import com.temp.model.Appointment;
import com.temp.model.Doctor;
import com.temp.model.Patient;
import com.temp.repository.AppointmentRepo;
import com.temp.repository.DoctorRepo;
import com.temp.repository.PatientRepo;
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
