package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.DTO.AppointmentCreateDto;
import com.Smart.Health.Care.Management.System.DTO.AppointmentDto;
import com.Smart.Health.Care.Management.System.Mapper.AppointmentMapper;
import com.Smart.Health.Care.Management.System.Model.Appointment;
import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.AppointmentRepo;
import com.Smart.Health.Care.Management.System.Repository.DoctorRepo;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // ✅ Add Appointment
    public String addAppointment(AppointmentCreateDto dto) {
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = appointmentMapper.toEntity(dto, patient, doctor);
        appointmentRepo.save(appointment);
        return "Appointment added";
    }

    // ✅ Get All Appointments
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Get Single Appointment by ID
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toDto(appointment);
    }

    // ✅ Update Appointment by ID
    public String updateAppointment(Long id, AppointmentCreateDto dto) {
        Appointment existingAppointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Fetch updated patient and doctor
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Update fields
        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);
        existingAppointment.setDate(dto.getDate());
        existingAppointment.setTime(dto.getTime());

        appointmentRepo.save(existingAppointment);
        return "Appointment updated";
    }

    // ✅ Delete Appointment by ID
    public String deleteAppointment(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepo.deleteById(id);
        return "Appointment deleted";
    }
}
