package com.temp.config;

import com.temp.repository.DoctorRepo;
import com.temp.repository.PatientRepo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public ApplicationConfiguration(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Try to find Patient first
            UserDetails user = patientRepo.findByPhoneNumber(username).orElse(null);
            if (user != null) {
                return user;
            }

            // Then try Doctor
            user = doctorRepo.findByEmail(username).orElse(null); // or by phone if preferred
            if (user != null) {
                return user;
            }

            // If neither found
            throw new UsernameNotFoundException("User not found with username: " + username);
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
