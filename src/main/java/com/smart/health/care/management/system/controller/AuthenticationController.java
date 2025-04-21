package com.smart.health.care.management.system.controller;
import com.smart.health.care.management.system.dto.DoctorCreateDto;
import com.smart.health.care.management.system.dto.LoginDoctorDto;
import com.smart.health.care.management.system.model.Doctor;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.dto.LoginUserDto;
import com.smart.health.care.management.system.dto.RegisterUserDto;
import com.smart.health.care.management.system.response.LoginResponse;
import com.smart.health.care.management.system.service.AuthenticationService;
import com.smart.health.care.management.system.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/patient/signup")
    public ResponseEntity<Patient> registerPatient(@RequestBody RegisterUserDto registerUserDto) {
        Patient registeredUser = authenticationService.signupPatient(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/doctor/signup")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorCreateDto doctorCreateDto) {
        Doctor registeredDoctor = authenticationService.signupDoctor(doctorCreateDto);

        return ResponseEntity.ok(registeredDoctor);
    }

    @PostMapping("/patient/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Patient authenticatedUser = authenticationService.authenticatePatient(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/doctor/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDoctorDto loginDoctorDto) {
        Doctor authenticatedDoctor = authenticationService.authenticateDoctor(loginDoctorDto);

        String jwtToken = jwtService.generateToken(authenticatedDoctor);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}