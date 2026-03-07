package com.juanCarlos.hardwareHub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
}
