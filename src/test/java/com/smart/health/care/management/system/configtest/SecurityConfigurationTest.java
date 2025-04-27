package com.smart.health.care.management.system.configtest;

import com.smart.health.care.management.system.config.JwtAuthenticationFilter;
import com.smart.health.care.management.system.config.SecurityConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SecurityConfigurationTest {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationProvider authenticationProvider;
    private SecurityConfiguration securityConfiguration;

    @BeforeEach
    void setUp() {
        jwtAuthenticationFilter = mock(JwtAuthenticationFilter.class);
        authenticationProvider = mock(AuthenticationProvider.class);
        securityConfiguration = new SecurityConfiguration(jwtAuthenticationFilter, authenticationProvider);
    }

    @Test
    void testCorsConfigurationSource() {
        CorsConfigurationSource corsSource = securityConfiguration.corsConfigurationSource();
        assertNotNull(corsSource);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        CorsConfiguration configuration = corsSource.getCorsConfiguration(mockRequest);
        assertNotNull(configuration);

        assertTrue(configuration.getAllowedOrigins().contains("http://localhost:8080"));
        assertTrue(configuration.getAllowedMethods().containsAll(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")));
        assertTrue(configuration.getAllowedHeaders().containsAll(List.of("Authorization", "Content-Type")));
        assertTrue(configuration.getAllowCredentials());
    }

}