package com.juanCarlos.hardwareHub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de respuesta de un usuario")
public class UsuarioResponseDto {

    @Schema(description = "Identificador único", example = "1")
    private Long id;
    @Schema(description = "Nombre de usuario", example = "juan_carlos")
    private String nombre;
    @Schema(description = "Correo electrónico", example = "juan@hardwarehub.es")
    private String email;
    @Schema(description = "Contraseña cifrada (no exponer en producción)", accessMode = Schema.AccessMode.READ_ONLY)
    private String contrasena;
    @Schema(description = "Rol del usuario en la plataforma", example = "USER")
    private UsuarioRol rol;

    @Schema(description = "Número de seguidores del usuario", example = "42")
    private int followersCount;

    @Schema(description = "Número de usuarios a los que sigue", example = "15")
    private int followingCount;
}
