package com.smart.health.care.management.system.configtest;

import com.smart.health.care.management.system.config.JwtAuthenticationFilter;
import com.smart.health.care.management.system.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerExceptionResolver;
import java.io.IOException;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private HandlerExceptionResolver handlerExceptionResolver;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        userDetailsService = mock(UserDetailsService.class);
        handlerExceptionResolver = mock(HandlerExceptionResolver.class);

        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, userDetailsService, handlerExceptionResolver);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
    }

    @Test
    void testDoFilterWithoutAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verifyNoInteractions(jwtService);
        verifyNoInteractions(userDetailsService);
    }

    @Test
    void testDoFilterWithInvalidAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("InvalidToken");

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verifyNoInteractions(jwtService);
        verifyNoInteractions(userDetailsService);
    }

    @Test
    void testDoFilterWithValidToken() throws ServletException, IOException {
        String jwtToken = "Bearer valid.token.here";
        String userEmail = "user@example.com";

        when(request.getHeader("Authorization")).thenReturn(jwtToken);
        when(jwtService.extractUsername("valid.token.here")).thenReturn(userEmail);
        when(jwtService.isTokenValid(eq("valid.token.here"), any(UserDetails.class))).thenReturn(true);

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getAuthorities()).thenReturn(new java.util.ArrayList<>());
        when(userDetailsService.loadUserByUsername(userEmail)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(jwtService, times(1)).extractUsername("valid.token.here");
        verify(userDetailsService, times(1)).loadUserByUsername(userEmail);
        verify(jwtService, times(1)).isTokenValid("valid.token.here", userDetails);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterExceptionHandling() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid.token");
        when(jwtService.extractUsername("invalid.token")).thenThrow(new RuntimeException("Invalid token"));

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(handlerExceptionResolver, times(1))
                .resolveException(eq(request), eq(response), isNull(), any(RuntimeException.class));
    }
}