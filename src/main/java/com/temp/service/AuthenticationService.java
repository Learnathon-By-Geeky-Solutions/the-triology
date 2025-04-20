package com.temp.service;
import com.temp.dto.DoctorCreateDto;
import com.temp.dto.LoginDoctorDto;
import com.temp.dto.LoginUserDto;
import com.temp.dto.RegisterUserDto;
import com.temp.model.Doctor;
import com.temp.model.Patient;
import com.temp.repository.DoctorRepo;
import com.temp.repository.PatientRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PatientRepo patientRepo;

    private final DoctorRepo doctorRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            PatientRepo patientRepo,
            DoctorRepo doctorRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Patient signupPatient(RegisterUserDto input) {
        Patient patient = new Patient()
                .setName(input.getName())
                .setPhoneNumber(input.getPhoneNumber())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setDateOfBirth(input.getDateOfBirth());

        return patientRepo.save(patient);
    }

    public Doctor signupDoctor(DoctorCreateDto input) {
        Doctor doctor = new Doctor()
                .setName(input.getDoctorName())
                .setSpecialty(input.getDoctorSpeciality())
                .setExperience(input.getDoctorExperience())
                .setEmail(input.getDoctorEmail())
                .setPhone(input.getDoctorPhone())
                .setPassword(passwordEncoder.encode(input.getDoctorPassword()));

        return doctorRepo.save(doctor);
    }

    public Patient authenticatePatient(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getPhoneNumber(),
                        input.getPassword()
                )
        );

        return patientRepo.findByPhoneNumber(input.getPhoneNumber())
                .orElseThrow();
    }

    public Doctor authenticateDoctor(LoginDoctorDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return doctorRepo.findByEmail(input.getEmail())
                .orElseThrow();
    }
}