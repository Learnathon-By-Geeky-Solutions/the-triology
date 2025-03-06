package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.Model.Appointment;
import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.AppointmentRepo;
import com.Smart.Health.Care.Management.System.Repository.DoctorRepo;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;

    public String addAppointment(Appointment appointment) {
        // Check if patient exists
        Patient patient = patientRepo.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Check if doctor exists
        Doctor doctor = doctorRepo.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointmentRepo.save(appointment);
        return "Appointment added";
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }
    public Appointment getAppointment(Long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
    public String updateAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
        return "Appointment updated";
    }
    public String deleteAppointment(Long id) {
        appointmentRepo.deleteById(id);
        return "Appointment deleted";
    }

}
