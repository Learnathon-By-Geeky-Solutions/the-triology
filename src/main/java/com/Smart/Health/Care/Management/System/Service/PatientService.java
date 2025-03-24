package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.Model.Patient;
import com.Smart.Health.Care.Management.System.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public String addPatient(Patient patient) {
        patientRepo.save(patient);
        return "Patient added";
    }
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
    public Patient getPatientById(int id) {
        return patientRepo.findById(id).orElseThrow(RuntimeException::new);
    }
    public String updatePatient(Patient patient) {
        patientRepo.save(patient);
        return "Patient updated";
    }
    public String deletePatient(int id) {
        patientRepo.deleteById(id);
        return "Patient deleted";
    }

}
