package com.Smart.Health.Care.Management.System.Service;

import com.Smart.Health.Care.Management.System.Model.Doctor;
import com.Smart.Health.Care.Management.System.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    public String addDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return "Doctor added successfully";
    }
    public List<Doctor> getAllDoctors() {return doctorRepo.findAll();}
    public Doctor getDoctorById(int id) {return doctorRepo.findById(id).orElseThrow(RuntimeException::new);}

    public String updateDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return "Doctor updated successfully";
    }
    public String deleteDoctor(int id) {
        doctorRepo.deleteById(id);
        return "Doctor deleted successfully";
    }

}
