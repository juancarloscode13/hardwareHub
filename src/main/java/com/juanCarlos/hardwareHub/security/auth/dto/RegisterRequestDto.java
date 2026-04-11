package com.juanCarlos.hardwareHub.security.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para el registro de un nuevo usuario.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de entrada para el registro público de un nuevo usuario")
public class RegisterRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre de usuario", example = "juan_carlos")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Schema(description = "Correo electrónico", example = "juan@hardwarehub.es")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Schema(description = "Contraseña en texto plano (se almacenará cifrada)", example = "P@ssw0rd!")
    private String contrasena;

    @Schema(description = "Icono de perfil del usuario (imagen en bytes)")
    private byte[] iconoPerfil;
}

