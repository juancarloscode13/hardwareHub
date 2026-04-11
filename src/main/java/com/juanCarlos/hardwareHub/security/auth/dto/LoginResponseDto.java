package com.juanCarlos.hardwareHub.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta de inicio de sesión.
 * Contiene el nombre de usuario y el rol del usuario autenticado.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto{
    private String username;
    private String role;
}
