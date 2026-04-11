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

/**
 * Beans relacionados con la autenticación: encoder de contraseñas,
 * `AuthenticationProvider`, `AuthenticationManager` y control de errores
 * (401/403) en formato JSON. También deshabilita el registro automático
 * del filtro JWT para que Spring Security lo gestione manualmente.
 *
 * @author Juan Carlos
 */
@Configuration
public class AuthenticationConfig {

    /**
     * Provee el `PasswordEncoder` (BCrypt) usado para almacenar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Proveedor de autenticación basado en UserDetailsService + BCrypt.
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
     * Desactiva el registro automático del filtro JWT para que lo gestione
     * Spring Security a través de la configuración manual (addFilterBefore).
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
