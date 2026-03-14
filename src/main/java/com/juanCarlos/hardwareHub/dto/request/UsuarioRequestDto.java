package com.juanCarlos.hardwareHub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de entrada para crear o actualizar un usuario")
public class UsuarioRequestDto {

    @Schema(description = "Nombre de usuario", example = "juan_carlos")
    private String nombre;
    @Schema(description = "Correo electrónico", example = "juan@hardwarehub.es")
    private String email;
    @Schema(description = "Contraseña (se almacenará cifrada)", example = "P@ssw0rd!")
    private String contrasena;
    @Schema(description = "Rol del usuario en la plataforma", example = "USER")
    private UsuarioRol rol;
}
