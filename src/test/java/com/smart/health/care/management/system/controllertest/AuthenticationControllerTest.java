package com.smart.health.care.management.system.controllertest;

import com.smart.health.care.management.system.controller.AuthenticationController;
import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.LoginDoctorDto;
import com.smart.health.care.management.system.dto.LoginUserDto;
import com.smart.health.care.management.system.dto.RegisterUserDto;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.response.LoginResponse;
import com.smart.health.care.management.system.service.AuthenticationService;
import com.smart.health.care.management.system.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        authenticationService = mock(AuthenticationService.class);
        authenticationController = new AuthenticationController(jwtService, authenticationService);
    }

    @Test
    void testRegisterPatient() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        Patient patient = new Patient();

        when(authenticationService.signupPatient(registerUserDto)).thenReturn(patient);

        ResponseEntity<Patient> response = authenticationController.registerPatient(registerUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());

        verify(authenticationService, times(1)).signupPatient(registerUserDto);
    }

    @Test
    void testRegisterDoctor() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto();
        Doctor doctor = new Doctor();

        when(authenticationService.signupDoctor(doctorCreateDto)).thenReturn(doctor);

        ResponseEntity<Doctor> response = authenticationController.registerDoctor(doctorCreateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor, response.getBody());

        verify(authenticationService, times(1)).signupDoctor(doctorCreateDto);
    }

    @Test
    void testAuthenticatePatient() {
        LoginUserDto loginUserDto = new LoginUserDto();
        Patient patient = new Patient();

        when(authenticationService.authenticatePatient(loginUserDto)).thenReturn(patient);
        when(jwtService.generateToken(patient)).thenReturn("jwt-patient-token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        ResponseEntity<LoginResponse> response = authenticationController.authenticate(loginUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jwt-patient-token", response.getBody().getToken());
        assertEquals(3600L, response.getBody().getExpiresIn());

        verify(authenticationService, times(1)).authenticatePatient(loginUserDto);
        verify(jwtService, times(1)).generateToken(patient);
        verify(jwtService, times(1)).getExpirationTime();
    }

    @Test
    void testAuthenticateDoctor() {
        LoginDoctorDto loginDoctorDto = new LoginDoctorDto();
        Doctor doctor = new Doctor();

        when(authenticationService.authenticateDoctor(loginDoctorDto)).thenReturn(doctor);
        when(jwtService.generateToken(doctor)).thenReturn("jwt-doctor-token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        ResponseEntity<LoginResponse> response = authenticationController.authenticate(loginDoctorDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jwt-doctor-token", response.getBody().getToken());
        assertEquals(3600L, response.getBody().getExpiresIn());

        verify(authenticationService, times(1)).authenticateDoctor(loginDoctorDto);
        verify(jwtService, times(1)).generateToken(doctor);
        verify(jwtService, times(1)).getExpirationTime();
    }
}