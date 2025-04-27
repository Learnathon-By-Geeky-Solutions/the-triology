package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.LoginDoctorDto;
import com.smart.health.care.management.system.dto.LoginUserDto;
import com.smart.health.care.management.system.dto.RegisterUserDto;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.DoctorRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import com.smart.health.care.management.system.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    private PatientRepo patientRepo;
    private DoctorRepo doctorRepo;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        patientRepo = mock(PatientRepo.class);
        doctorRepo = mock(DoctorRepo.class);
        authenticationManager = mock(AuthenticationManager.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authenticationService = new AuthenticationService(patientRepo, doctorRepo, authenticationManager, passwordEncoder);
    }

    @Test
    void testSignupPatient() {
        RegisterUserDto registerUserDto = new RegisterUserDto()
                .setName("John Doe")
                .setPhoneNumber("1234567890")
                .setPassword("password")
                .setDateOfBirth(LocalDate.of(1990, 1, 1));

        Patient savedPatient = new Patient();
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(patientRepo.save(any(Patient.class))).thenReturn(savedPatient);

        Patient result = authenticationService.signupPatient(registerUserDto);

        assertNotNull(result);
        verify(patientRepo, times(1)).save(any(Patient.class));
    }

    @Test
    void testSignupDoctor() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto();
        doctorCreateDto.setDoctorName("Dr. Smith");
        doctorCreateDto.setDoctorSpeciality("Cardiology");
        doctorCreateDto.setDoctorExperience("10 years");
        doctorCreateDto.setDoctorEmail("drsmith@example.com");
        doctorCreateDto.setDoctorPhone("9876543210");
        doctorCreateDto.setDoctorPassword("docpass");

        Doctor savedDoctor = new Doctor();
        when(passwordEncoder.encode("docpass")).thenReturn("encodedDocPass");
        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        Doctor result = authenticationService.signupDoctor(doctorCreateDto);

        assertNotNull(result);
        verify(doctorRepo, times(1)).save(any(Doctor.class));
    }

    @Test
    void testAuthenticatePatient() {
        LoginUserDto loginUserDto = new LoginUserDto()
                .setPhoneNumber("1234567890")
                .setPassword("password");

        Patient patient = new Patient();
        when(patientRepo.findByPhoneNumber("1234567890")).thenReturn(Optional.of(patient));

        Patient result = authenticationService.authenticatePatient(loginUserDto);

        assertNotNull(result);

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        verify(patientRepo, times(1)).findByPhoneNumber("1234567890");
    }

    @Test
    void testAuthenticateDoctor() {
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto()
                .setEmail("drsmith@example.com")
                .setPassword("docpass");

        Doctor doctor = new Doctor();
        when(doctorRepo.findByEmail("drsmith@example.com")).thenReturn(Optional.of(doctor));

        Doctor result = authenticationService.authenticateDoctor(loginDoctorDto);

        assertNotNull(result);

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        verify(doctorRepo, times(1)).findByEmail("drsmith@example.com");
    }

    @Test
    void testAuthenticatePatient_NotFound() {
        LoginUserDto loginUserDto = new LoginUserDto()
                .setPhoneNumber("1234567890")
                .setPassword("password");

        when(patientRepo.findByPhoneNumber("1234567890")).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> authenticationService.authenticatePatient(loginUserDto));
    }

    @Test
    void testAuthenticateDoctor_NotFound() {
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto()
                .setEmail("drsmith@example.com")
                .setPassword("docpass");

        when(doctorRepo.findByEmail("drsmith@example.com")).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> authenticationService.authenticateDoctor(loginDoctorDto));
    }
}