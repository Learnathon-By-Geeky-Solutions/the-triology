package com.smart.health.care.management.system.configtest;

import com.smart.health.care.management.system.config.ApplicationConfiguration;
import com.smart.health.care.management.system.model.Patient;
import com.smart.health.care.management.system.repository.DoctorRepo;
import com.smart.health.care.management.system.repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationConfigurationTest {

    private PatientRepo patientRepo;
    private DoctorRepo doctorRepo;
    private ApplicationConfiguration applicationConfiguration;

    @BeforeEach
    void setUp() {
        patientRepo = mock(PatientRepo.class);
        doctorRepo = mock(DoctorRepo.class);
        applicationConfiguration = new ApplicationConfiguration(patientRepo, doctorRepo);
    }

    @Test
    void testUserDetailsServiceReturnsPatient() {
        Patient patient = new Patient();
        patient.setPhoneNumber("01712345678");

        when(patientRepo.findByPhoneNumber("01712345678")).thenReturn(java.util.Optional.of(patient));

        UserDetailsService service = applicationConfiguration.userDetailsService();
        assertNotNull(service.loadUserByUsername("01712345678"));
    }

    @Test
    void testUserDetailsServiceThrowsExceptionWhenUserNotFound() {
        when(patientRepo.findByPhoneNumber("000")).thenReturn(java.util.Optional.empty());
        when(doctorRepo.findByEmail("000")).thenReturn(java.util.Optional.empty());

        UserDetailsService service = applicationConfiguration.userDetailsService();
        assertThrows(
                org.springframework.security.core.userdetails.UsernameNotFoundException.class,
                () -> service.loadUserByUsername("000")
        );
    }

    @Test
    void testPasswordEncoderBean() {
        BCryptPasswordEncoder encoder = applicationConfiguration.passwordEncoder();
        assertNotNull(encoder);
        assertTrue(encoder.matches("password", encoder.encode("password")));
    }

    @Test
    void testAuthenticationProviderBean() {
        AuthenticationProvider provider = applicationConfiguration.authenticationProvider();
        assertNotNull(provider);
        assertTrue(provider instanceof DaoAuthenticationProvider);
    }

    @Test
    void testAuthenticationManagerBean() throws Exception {
        AuthenticationConfiguration mockConfig = mock(AuthenticationConfiguration.class);
        AuthenticationManager mockManager = mock(AuthenticationManager.class);

        when(mockConfig.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager manager = applicationConfiguration.authenticationManager(mockConfig);
        assertNotNull(manager);
    }
}