package com.juanCarlos.hardwareHub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.juanCarlos.hardwareHub.entity.enums.UsuarioRol;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    private String nombre;
    private String email;
    private String contrasena;
    private UsuarioRol rol;
}
