package com.smart.health.care.management.system.servicetest;

import com.smart.health.care.management.system.service.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private final String secretKey = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"; // 64 bytes for HS256
    private final long expirationTime = 3600000; // 1 hour in milliseconds

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        // manually inject values because @Value is not processed in unit tests
        jwtService.getClass().getDeclaredFields();
        try {
            var secretField = JwtService.class.getDeclaredField("secretKey");
            secretField.setAccessible(true);
            secretField.set(jwtService, secretKey);

            var expirationField = JwtService.class.getDeclaredField("jwtExpiration");
            expirationField.setAccessible(true);
            expirationField.set(jwtService, expirationTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("testuser");
    }

    @Test
    void testGenerateAndValidateToken() {
        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);

        String extractedUsername = jwtService.extractUsername(token);
        assertEquals("testuser", extractedUsername);

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testExtractClaim() {
        String token = jwtService.generateToken(userDetails);

        Claims claims = jwtService.extractClaim(token, c -> c);
        assertEquals("testuser", claims.getSubject());
    }

    @Test
    void testIsTokenExpired_False() {
        String token = jwtService.generateToken(userDetails);

        boolean expired = jwtService.isTokenValid(token, userDetails);
        assertTrue(expired);
    }

    @Test
    void testGetExpirationTime() {
        assertEquals(expirationTime, jwtService.getExpirationTime());
    }

    @Test
    void testGenerateTokenWithExtraClaims() {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "admin");

        String token = jwtService.generateToken(extraClaims, userDetails);

        assertNotNull(token);

        Claims claims = jwtService.extractClaim(token, c -> c);
        assertEquals("admin", claims.get("role"));
    }
}