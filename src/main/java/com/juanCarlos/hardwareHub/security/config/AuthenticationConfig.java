package com.juanCarlos.hardwareHub.security.config;

import com.juanCarlos.hardwareHub.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.LocalDateTime;

@Configuration
public class AuthenticationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Proveedor de autenticación DAO basado en UserDetailsService + BCrypt.
     * Devuelve AuthenticationProvider para que pueda inyectarse directamente en SecurityFilterChain.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Impide que Spring Boot registre JwtAuthenticationFilter como servlet filter genérico.
     * Sin esto, el filtro se ejecuta DOS VECES: una fuera de Spring Security (como servlet filter)
     * y otra dentro del SecurityFilterChain, lo que interfiere con el SecurityContextHolder
     * y provoca 401 en rutas públicas como /auth/login.
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean<?> jwtFilterRegistration(JwtAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * Devuelve HTTP 401 con cuerpo JSON cuando la petición no incluye credenciales válidas.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"status\":401,\"message\":\"Autenticación requerida: " + authException.getMessage() + "\",\"timestamp\":\"" + LocalDateTime.now() + "\"}"
            );
        };
    }

    /**
     * Devuelve HTTP 403 con cuerpo JSON cuando el usuario autenticado no tiene permisos suficientes.
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"status\":403,\"message\":\"Acceso denegado: " + accessDeniedException.getMessage() + "\",\"timestamp\":\"" + LocalDateTime.now() + "\"}"
            );
        };
    }
}
